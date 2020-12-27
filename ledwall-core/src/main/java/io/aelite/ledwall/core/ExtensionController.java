package io.aelite.ledwall.core;

import io.aelite.ledwall.core.plugin.LedWallPlugin;
import io.aelite.ledwall.core.plugin.PluginController;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExtensionController {

    private Reflections reflections;
    private PluginController pluginController;

    public ExtensionController() throws IllegalAccessException, InstantiationException {
        this.reflections = new Reflections("");
        //this.pluginController = this.createPluginController();
    }

    private void createPluginController() throws InstantiationException, IllegalAccessException {
        Set<Class<?>> pluginClasses = this.scanPlugins();
        List<Object> plugins = this.instantiateExtensions(pluginClasses);
        //return new PluginController(plugins);
    }

    private Set<Class<?>> scanPlugins() {
        return reflections.getTypesAnnotatedWith(LedWallPlugin.class);
    }

    private List<Object> instantiateExtensions(Set<Class<?>> classes) throws IllegalAccessException, InstantiationException {
        List<Object> instances = new ArrayList<Object>();
        for(Class aClass : classes){
            instances.add(aClass.newInstance());
        }
        return instances;
    }

}
