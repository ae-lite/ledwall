package io.aelite.ledwall.core;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.aelite.ledwall.core.blendmode.BlendMode;
import io.aelite.ledwall.core.blendmode.NormalBlendMode;

public class GuiceCoreModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public BlendMode normalBlendMode(){
        return new NormalBlendMode();
    }

}
