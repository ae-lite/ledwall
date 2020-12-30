package io.aelite.ledwall.layerpackage;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.Color;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.animation.layer.LedWallAnimationLayer;
import io.aelite.ledwall.core.animation.layer.control.LedWallControl;

@LedWallAnimationLayer(name = "Static Color", description = "Lights up the entire LedWall in one color.")
public class StaticColor extends AnimationLayer {

    @LedWallControl("Color")
    private Color color;

    @Override
    public void onInit() {
        this.color = new Color(255, 255, 255);
    }

    @Override
    public void onUpdate(Canvas canvas, double deltaTime) {
        canvas.fill(this.color.get());
    }

    @Override
    public void onStop() {

    }

    public Color getColor(){
        return this.color;
    }

}
