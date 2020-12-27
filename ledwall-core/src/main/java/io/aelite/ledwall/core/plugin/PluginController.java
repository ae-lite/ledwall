package io.aelite.ledwall.core.plugin;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PluginController {

    private static Logger logger = LoggerFactory.getLogger(PluginController.class);

    private List<Object> plugins;

    public PluginController(){
        this.plugins = new ArrayList<Object>();
    }

    public void loadPlugins(Reflections reflections) {
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(LedWallPlugin.class);
        for(Class aClass : classes){
            try {
                this.plugins.add(aClass.newInstance());
            } catch (Exception e) {
                logger.error("Plugin failed to load: " + aClass.getName());
                e.printStackTrace();
            }
        }
    }

    public void initPlugins(){
        for(Object plugin : this.plugins){
            if(plugin instanceof OnInit){
                try{
                    ((OnInit) plugin).onInit();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void runPlugins(){
        for(Object plugin : this.plugins){
            if(plugin instanceof OnRun){
                new Thread(() -> {
                    try {
                        ((OnRun) plugin).onRun();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    public void stopPlugins(){
        for(Object plugin : this.plugins){
            if(plugin instanceof OnStop){
                try{
                    ((OnStop) plugin).onStop();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
