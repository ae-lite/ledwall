package io.aelite.ledwall.sandbox;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.layerpackage.StaticColor;

public class Main {

    public static void main(String[] args) {
        LedWallApplication app = LedWallApplication.INSTANCE;

        Animation animation = new Animation("Test Animation");
        animation.addLayer(new StaticColor());
        app.getAnimationController().addAnimation(animation);
        app.getAnimationController().runAnimation(animation);

        app.run();
    }

}
