package io.aelite.ledwall.sandbox;

import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.AnimationLayer;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        LedWallApplication.INSTANCE.run();

        Animation animation = new Animation("Test Animation");
        animation.addAnimationLayer((AnimationLayer) Class.forName("io.aelite.ledwall.layerpackage.StaticColor").newInstance());
        animation.addAnimationLayer((AnimationLayer) Class.forName("io.aelite.ledwall.layerpackage.Strobe").newInstance());
        LedWallApplication.INSTANCE.getAnimationManager().getAnimationPlayer().play(animation);
    }

}
