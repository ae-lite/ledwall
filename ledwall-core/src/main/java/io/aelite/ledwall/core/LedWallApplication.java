package io.aelite.ledwall.core;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
        this.pluginManager.initPlugins();
        new Thread(this.animationPlayer::runRenderLoop).start();
    }

    public void stop() {
        this.animationPlayer.stop();
        this.pluginManager.stopPlugins();
        System.exit(0);
    }

}
