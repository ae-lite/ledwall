package io.aelite.ledwall.core;

import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.core.animation.AnimationManager;
import io.aelite.ledwall.core.animation.AnimationPlayer;
import io.aelite.ledwall.core.animation.layer.AnimationLayerBuilder;
import io.aelite.ledwall.core.animation.layer.AnimationLayerManager;
import io.aelite.ledwall.core.plugin.PluginLoader;
import io.aelite.ledwall.core.plugin.PluginManager;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * This class represents the whole application state in one singleton instance.
 * It also acts as facade, to interact with subcomponents.
 * Plugins should only interact with the interface of this class (Law of Demeter).
 *
 * @author David Adamson
 */
public class LedWallApplication {

    /**
     * This is the singleton instance representing the whole applications state.
     */
    public static final LedWallApplication INSTANCE = new LedWallApplication();

    private AnimationManager animationManager;
    private AnimationLayerManager animationLayerManager;
    private AnimationPlayer animationPlayer;
    private PluginManager pluginManager;

    /**
     * This constructs the LedWallApplication instance.
     * Note: This constructor is private due to the singleton design pattern.
     */
    private LedWallApplication(){
        this.animationManager = new AnimationManager();
        this.animationLayerManager = new AnimationLayerManager();
        // TODO load from properties
        this.animationPlayer = new AnimationPlayer(60, 48, 12);
        this.pluginManager = new PluginManager(new PluginLoader().loadPlugins());
    }

    /**
     * Runs the Application.
     * It loads and initializes plugins provided as submodules, then starts the render loop.
     */
    public void run() {
        this.pluginManager.initPlugins();
        this.animationPlayer.startRenderer();
    }

    /**
     * Stops and closes the Application.
     * This includes stopping the renderer such as stopping plugins.
     */
    public void stop() {
        this.animationPlayer.stop();
        this.pluginManager.stopPlugins();
        System.exit(0);
    }

    /**
     * Plays the provides animation using the animationPlayer instance.
     * @param animation to run
     */
    public void playAnimation(Animation animation) {
        this.animationPlayer.play(animation);
    }

    /**
     * Subscribes to frame updates generated by the renderer of the animationPlayer.
     * @param frameUpdateCallback is called when there is a new frame update
     */
    public void onFrameUpdate(Consumer<Canvas> frameUpdateCallback) {
        this.animationPlayer.onFrameUpdate(frameUpdateCallback);
    }

    /**
     * Get the registered animations.
     * @return a list of playable animations
     */
    public List<Animation> getAnimations() {
        return this.animationManager.getAnimations();
    }

    /**
     * Get the animation by id.
     * @param id of the animation
     * @return the corresponding animation
     */
    public Animation getAnimation(UUID uuid) {
        return this.animationManager.getAnimation(uuid);
    }

    /**
     * Register a new animation.
     * @param animation to register
     */
    public void addAnimation(Animation animation) {
        this.animationManager.addAnimation(animation);
    }

    /**
     * Register an AnimationLayerBuilder that is responsible for creating new animation layers.
     * @param builder the object that instantiates new AnimationLayers
     */
    public void addAnimationLayerBuilder(AnimationLayerBuilder builder) {
        this.animationLayerManager.addAnimationLayerBuilder(builder);
    }

    /**
     * Get all registered AnimationLayerBuilder.
     * @return builders
     */
    public List<AnimationLayerBuilder> getAnimationLayerBuilders(){
        return this.animationLayerManager.getAnimationLayerBuilders();
    }

    /**
     * Get the AnimationLayerBuilder by UUID.
     * @param layerBuilderUuid uuid to search for
     * @return the object that instantiates new AnimationLayers
     */
    public AnimationLayerBuilder getAnimationLayerBuilder(UUID layerBuilderUuid) {
        return this.animationLayerManager.getAnimationLayerBuilder(layerBuilderUuid);
    }
}
