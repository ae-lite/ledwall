package io.aelite.ledwall.core;

import io.aelite.ledwall.core.layer.AnimationLayerController;
import io.aelite.ledwall.core.plugin.PluginController;
import org.reflections.Reflections;

public class LedWallApplication {

    public static LedWallApplication INSTANCE = new LedWallApplication();

    private DispatcherLedWall ledWall;
    private PluginController pluginController;
    private AnimationLayerController animationLayerController;

    private LedWallApplication(){
        // TODO: load dimensions from config
        this.ledWall = new DispatcherLedWall(32, 18);
        this.pluginController = new PluginController();
        this.animationLayerController = new AnimationLayerController();
    }

    public void run() {
        Reflections reflections = new Reflections("");
        this.animationLayerController.loadAnimationLayerFactories(reflections);
        this.pluginController.loadPlugins(reflections);
        this.pluginController.initPlugins();
        this.pluginController.runPlugins();
    }

    public void stop(){
        this.pluginController.stopPlugins();
        System.exit(0);
    }

    public DispatcherLedWall getLedWall() {
        return this.ledWall;
    }

    public AnimationLayerController getAnimationLayerController() {
        return animationLayerController;
    }
}
