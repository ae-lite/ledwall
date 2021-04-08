package io.aelite.ledwall.core.plugin;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PluginLoader {

    private static final Logger logger = LoggerFactory.getLogger(PluginLoader.class);

    public Set<Plugin> loadPlugins(Class<?>... classes){
        ConfigurationBuilder builder = new ConfigurationBuilder().forPackages(
                "io.aelite.ledwall",
                Arrays.stream(classes).map(aClass -> aClass.getPackage().getName()).collect(Collectors.joining())
        );
        Reflections reflections = new Reflections(builder);

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
