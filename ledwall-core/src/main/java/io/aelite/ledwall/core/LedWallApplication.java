package io.aelite.ledwall.core;

import io.aelite.ledwall.core.animation.AnimationController;
import io.aelite.ledwall.core.plugin.PluginController;
import org.reflections.Reflections;

public class LedWallApplication {

    public static LedWallApplication INSTANCE = new LedWallApplication();

    private DispatcherLedWall ledWall;
    private PluginController pluginController;
    private AnimationController animationController;

    private LedWallApplication(){
        // TODO: load dimensions from config
        this.ledWall = new DispatcherLedWall(32, 18);
        this.pluginController = new PluginController();
        this.animationController = new AnimationController();
    }

    public void run() {
        Reflections reflections = new Reflections("");
        this.pluginController.loadPlugins(reflections);
        this.pluginController.initPlugins();
        this.pluginController.runPlugins();

        this.animationController.run();
    }

    public void stop(){
        this.pluginController.stopPlugins();
        System.exit(0);
    }

    public DispatcherLedWall getLedWall() {
        return this.ledWall;
    }

    /*public AnimationLayerController getAnimationLayerController() {
        //TODO return animationLayerController;
    }*/

    public AnimationController getAnimationController(){
        return this.animationController;
    }

}
