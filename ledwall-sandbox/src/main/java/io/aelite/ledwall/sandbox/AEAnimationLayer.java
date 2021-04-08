package io.aelite.ledwall.sandbox;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.util.Color;

public class AEAnimationLayer extends AnimationLayer {

    private double red;

    public AEAnimationLayer(String name) {
        super(name);
    }

    @Override public void onInit() {}

    // TODO implement some fancy animation!
    @Override public void onUpdate(Canvas canvas, double deltaTime) {
        this.red = (this.red + deltaTime * 30) % 255;
        canvas.fill(Color.getARGB(255, (int) this.red, 0, 0));
    }

    @Override public void onStop() {}

}
