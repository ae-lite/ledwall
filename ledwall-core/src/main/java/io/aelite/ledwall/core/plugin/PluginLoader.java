package io.aelite.ledwall.core.plugin;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class PluginLoader {

    private static final Logger logger = LoggerFactory.getLogger(PluginLoader.class);

    public Set<Plugin> loadPlugins(){
        Reflections reflections = new Reflections("");

        return reflections.getSubTypesOf(Plugin.class).stream().map(clazz -> {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                logger.error("Failed to instantiate plugin", e);
            }
            return null;
        }).collect(Collectors.toSet());
    }

}
