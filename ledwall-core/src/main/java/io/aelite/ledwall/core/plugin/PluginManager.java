package io.aelite.ledwall.core.plugin;

import io.aelite.ledwall.core.LedWallApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PluginManager {

    private static final Logger logger = LoggerFactory.getLogger(PluginManager.class);

    private LedWallApplication application;
    private final Set<Plugin> plugins;

    public PluginManager(LedWallApplication application, Set<Plugin> plugins){
        this.application = application;
        this.plugins = plugins;
    }

    public void initPlugins(){
        for(Plugin plugin : this.plugins){
            try{
                plugin.onInit(this.application);
            } catch(Exception e){
                e.printStackTrace();
                logger.error("Could not initialize plugin", e);
            }
        }
    }

    public void stopPlugins(){
        for(Plugin plugin : this.plugins){
            try{
                plugin.onStop();
            } catch(Exception e){
                logger.error("Could not stop plugin", e);
            }
        }
    }

}
