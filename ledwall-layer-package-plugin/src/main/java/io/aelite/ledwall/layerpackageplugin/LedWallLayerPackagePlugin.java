package io.aelite.ledwall.layerpackageplugin;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.plugin.Plugin;

public class LedWallLayerPackagePlugin implements Plugin {

    @Override
    public void onInit() {
        LedWallApplication.INSTANCE.addAnimationLayerBuilder(new StaticColor.Builder());
        LedWallApplication.INSTANCE.addAnimationLayerBuilder(new Strobe.Builder());
    }

    @Override
    public void onStop() {

    }

}
