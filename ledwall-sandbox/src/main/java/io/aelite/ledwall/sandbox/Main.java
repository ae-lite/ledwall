package io.aelite.ledwall.sandbox;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;

public class Main {

    public static void main(String[] args) {
        LedWallApplication.INSTANCE.run();

        Animation animation = new Animation("Test Animation");
        LedWallApplication.INSTANCE.playAnimation(animation);
    }

}
