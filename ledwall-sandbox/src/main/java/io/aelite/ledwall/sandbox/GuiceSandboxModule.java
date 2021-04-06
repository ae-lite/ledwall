package io.aelite.ledwall.sandbox;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class GuiceSandboxModule extends AbstractModule {

    @Provides
    @Named("ledwall.core.width")
    public int ledwallWidth(){
        return 48;
    }

    @Provides
    @Named("ledwall.core.height")
    public int ledwallHeight(){
        return 12;
    }

    @Provides
    @Named("ledwall.rest.port")
    public int ledwallRestPort(){
        return 8080;
    }

}
