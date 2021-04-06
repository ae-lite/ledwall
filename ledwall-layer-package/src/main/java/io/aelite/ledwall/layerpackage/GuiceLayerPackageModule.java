package io.aelite.ledwall.layerpackage;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;

public class GuiceLayerPackageModule extends AbstractModule {

    @ProvidesIntoSet
    public Class<? extends AnimationLayer> staticColor(){
        return StaticColor.class;
    }

    @ProvidesIntoSet
    public Class<? extends AnimationLayer> strobe(){
        return Strobe.class;
    }

}
