package io.aelite.ledwall.layerpackageplugin;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.layer.AnimationLayerBuilder;
import io.aelite.ledwall.core.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LedWallLayerPackagePlugin implements Plugin {

    private static final Logger logger = LoggerFactory.getLogger(LedWallLayerPackagePlugin.class);

    @Override
    public void onInit(LedWallApplication application) {
        application.addAnimationLayerBuilder(new AnimationLayerBuilder("Static Color", "Lights up the LedWall in one color.", StaticColor::new));
        application.addAnimationLayerBuilder(new AnimationLayerBuilder("Strobe", "The LedWall will blink in an adjustable frequency.", Strobe::new));

        logger.info("LedWallLayerPackagePlugin initialized");
    }

    @Override
    public void onStop() {
        logger.info("LedWallLayerPackagePlugin stopped");
    }

}
