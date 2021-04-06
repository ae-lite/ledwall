package io.aelite.ledwall.fxplugin;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import io.aelite.ledwall.core.Plugin;

public class GuiceSimulatorModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Plugin> pluginBinder = Multibinder.newSetBinder(binder(), Plugin.class);
        pluginBinder.addBinding().to(LedWallSimulatorPlugin.class).in(Singleton.class);
    }
}
