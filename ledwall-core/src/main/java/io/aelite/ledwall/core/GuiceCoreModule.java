package io.aelite.ledwall.core;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class GuiceCoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AnimationPlayer.class).to(AnimationPlayer.class).in(Singleton.class);
        bind(AnimationManager.class).to(AnimationManager.class).in(Singleton.class);
        bind(PluginManager.class).to(PluginManager.class).in(Singleton.class);
    }
}
