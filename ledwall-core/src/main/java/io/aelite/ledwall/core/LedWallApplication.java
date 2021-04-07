package io.aelite.ledwall.core;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.aelite.ledwall.core.animation.AnimationManager;
import io.aelite.ledwall.core.plugin.PluginManager;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class LedWallApplication {

    public static final LedWallApplication INSTANCE = new LedWallApplication();
    public static final Logger logger = LoggerFactory.getLogger(LedWallApplication.class);

    private AnimationManager animationManager;
    private PluginManager pluginManager;

    private LedWallApplication(){
        Set<? extends AbstractModule> guiceModules = this.findGuiceModules();
        Injector injector = Guice.createInjector(guiceModules);

        this.animationManager = injector.getInstance(AnimationManager.class);
        this.pluginManager = injector.getInstance(PluginManager.class);
    }

    public void run() {
        this.pluginManager.initPlugins();
        new Thread(
                this.animationManager.getAnimationPlayer()::runRenderLoop,
                "LedWall Render Thread"
        ).start();
    }

    public void stop() {
        this.animationManager.getAnimationPlayer().stop();
        this.pluginManager.stopPlugins();
        System.exit(0);
    }

    public AnimationManager getAnimationManager(){
        return this.animationManager;
    }

    public PluginManager getPluginManager(){
        return this.pluginManager;
    }

    private Set<? extends AbstractModule> findGuiceModules(){
        Reflections reflections = new Reflections("");

        return reflections.getSubTypesOf(AbstractModule.class).stream().map(clazz -> {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                logger.error("Failed to instantiate guice module", e);
            }
            return null;
        }).collect(Collectors.toSet());
    }

}
