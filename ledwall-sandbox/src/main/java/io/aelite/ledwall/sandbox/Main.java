package io.aelite.ledwall.sandbox;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.layerpackage.StaticColor;

public class Main {

    public static void main(String[] args) throws Exception {
        LedWallApplication application = LedWallApplication.INSTANCE;

        Animation animation = new Animation("Test Animation");
        animation.addLayer(new StaticColor());

        StaticColor second = new StaticColor();
        second.getColor().set(0x88_00_00_FF);
        animation.addLayer(second);

        application.getAnimationController().setRunningAnimation(animation);

        application.run();
    }

}
