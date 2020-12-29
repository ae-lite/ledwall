package io.aelite.ledwall.layerpackage;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.Color;
import io.aelite.ledwall.core.animation.control.LedWallControl;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.animation.layer.LedWallAnimationLayer;

@LedWallAnimationLayer(name = "Static Color", description = "Lights up the entire LedWall in one color.")
public class StaticColor extends AnimationLayer {

    @LedWallControl("Color")
    private Color color = new Color(255, 0, 0);

    @Override
    public void onInit() {

    }

    @Override
    public void onUpdate(Canvas canvas, long frame) {
        canvas.fill(Color.get(255, (int) (frame % 255), 0, 0));
    }

    @Override
    public void onStop() {

    }

    public Color getColor(){
        return this.color;
    }

}
