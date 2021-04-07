package io.aelite.ledwall.simulatorplugin;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import io.aelite.ledwall.core.plugin.Plugin;

public class GuiceSimulatorPluginModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Plugin> pluginBinder = Multibinder.newSetBinder(binder(), Plugin.class);
        pluginBinder.addBinding().to(LedWallSimulatorPlugin.class).in(Singleton.class);
    }
}
