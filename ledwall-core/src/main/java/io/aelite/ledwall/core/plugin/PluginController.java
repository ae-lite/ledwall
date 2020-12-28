package io.aelite.ledwall.core.plugin;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PluginController {

    private static Logger logger = LoggerFactory.getLogger(PluginController.class);

    private List<Plugin> plugins;

    public PluginController(){
        this.plugins = new ArrayList<Plugin>();
    }

    //TODO refactor loading to PluginLoader
    public void loadPlugins(Reflections reflections) {
        Set<Class<? extends Plugin>> classes = reflections.getSubTypesOf(Plugin.class);
        for(Class aClass : classes){
            try {
                if(aClass.isAnnotationPresent(LedWallPlugin.class)){
                    this.plugins.add((Plugin) aClass.newInstance());
                }
            } catch (Exception e) {
                logger.error("Plugin failed to load: " + aClass.getName());
                e.printStackTrace();
            }
        }
    }

    public void initPlugins(){
        for(Plugin plugin : this.plugins){
            try{
                plugin.onInit();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void runPlugins(){
        for(Plugin plugin : this.plugins){
            if(plugin instanceof PluginOnRun){
                new Thread(() -> {
                    try {
                        ((PluginOnRun) plugin).onRun();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    public void stopPlugins(){
        for(Plugin plugin : this.plugins){
            try{
                plugin.onStop();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
