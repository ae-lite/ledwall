package io.aelite.ledwall.sandbox;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.core.animation.layer.AnimationLayerBuilder;

public class Main {

    public static void main(String[] args) {
        LedWallApplication application = new LedWallApplication();

        AnimationLayerBuilder builder = new AnimationLayerBuilder("AE", "Default AE animation layer.", AEAnmationLayer::new);
        application.addAnimationLayerBuilder(builder);

        Animation animation = new Animation("Boot Animation");
        animation.addAnimationLayer(builder.build());

        application.addAnimation(animation);
        application.playAnimation(animation);

        application.run();
    }

}
