package io.aelite.ledwall.restplugin;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import io.aelite.ledwall.core.plugin.Plugin;

public class GuiceRestPluginModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Plugin> pluginBinder = Multibinder.newSetBinder(binder(), Plugin.class);
        pluginBinder.addBinding().to(LedWallRestPlugin.class).in(Singleton.class);
    }

}
