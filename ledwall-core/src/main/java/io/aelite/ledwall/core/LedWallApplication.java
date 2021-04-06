package io.aelite.ledwall.core;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.aelite.ledwall.core.animation.layer.AnimationLayerFactory;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

public class LedWallApplication {

    private AnimationManager animationManager;
    private AnimationPlayer animationPlayer;
    private PluginManager pluginManager;

    public LedWallApplication(){
        Set<? extends AbstractModule> guiceModules = this.findGuiceModules();
        Injector injector = Guice.createInjector(guiceModules);

        this.animationManager = injector.getInstance(AnimationManager.class);
        this.animationPlayer = injector.getInstance(AnimationPlayer.class);
        this.pluginManager = injector.getInstance(PluginManager.class);
    }

    private Set<? extends AbstractModule> findGuiceModules(){
        Reflections reflections = new Reflections("");

        return reflections.getSubTypesOf(AbstractModule.class).stream().map(clazz -> {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toSet());
    }

    public void run() {

        this.animationLayerFactory.loadInstantiators(reflections);
        this.pluginManager.loadPlugins(reflections);
        this.pluginManager.initPlugins();
        this.pluginManager.runPlugins();

        this.animationManager.run();
    }

    public void stop() {
        this.animationManager.stop();
        this.pluginManager.stopPlugins();
        System.exit(0);
    }

    public DispatcherLedWall getLedWall() {
        return this.ledWall;
    }

    public AnimationLayerFactory getAnimationLayerFactory() {
        return this.animationLayerFactory;
    }

    public AnimationManager getAnimationController(){
        return this.animationManager;
    }

}
