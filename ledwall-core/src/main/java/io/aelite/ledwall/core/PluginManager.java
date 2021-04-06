package io.aelite.ledwall.core;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PluginManager {

    private static final Logger logger = LoggerFactory.getLogger(PluginManager.class);
    private final Set<Plugin> plugins;

    @Inject
    public PluginManager(Set<Plugin> plugins){
        this.plugins = plugins;
    }

    public void initPlugins(){
        for(Plugin plugin : this.plugins){
            try{
                plugin.onInit();
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
