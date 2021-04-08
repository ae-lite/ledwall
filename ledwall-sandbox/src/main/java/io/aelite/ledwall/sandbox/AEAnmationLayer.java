package io.aelite.ledwall.sandbox;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;

public class AEAnmationLayer extends AnimationLayer {

    public AEAnmationLayer(String name) {
        super(name);
    }

    @Override public void onInit() {}

    // TODO implement some fancy animation!
    @Override public void onUpdate(Canvas canvas, double deltaTime) {
        canvas.fill(0xFF_FF_00_00);
    }

    @Override public void onStop() {}

}
