package io.aelite.ledwall.core;

import io.aelite.ledwall.core.animation.AnimationController;
import io.aelite.ledwall.core.animation.layer.AnimationLayerFactory;
import io.aelite.ledwall.core.plugin.PluginController;
import org.reflections.Reflections;

public class LedWallApplication {

    public static LedWallApplication INSTANCE = new LedWallApplication();

    private DispatcherLedWall ledWall;
    private PluginController pluginController;
    private AnimationLayerFactory animationLayerFactory;
    private AnimationController animationController;

    private LedWallApplication(){
        // TODO: load dimensions from config
        this.ledWall = new DispatcherLedWall(32, 18);
        this.pluginController = new PluginController();
        this.animationLayerFactory = new AnimationLayerFactory();
        this.animationController = new AnimationController();
    }

    public void run() {
        Reflections reflections = new Reflections("");
        this.animationLayerFactory.loadInstantiators(reflections);
        this.pluginController.loadPlugins(reflections);
        this.pluginController.initPlugins();
        this.pluginController.runPlugins();

        this.animationController.run();
    }

    public void stop() {
        this.animationController.stop();
        this.pluginController.stopPlugins();
        System.exit(0);
    }

    public DispatcherLedWall getLedWall() {
        return this.ledWall;
    }

    public AnimationLayerFactory getAnimationLayerFactory() {
        return this.animationLayerFactory;
    }

    public AnimationController getAnimationController(){
        return this.animationController;
    }

}
