package io.aelite.ledwall.layerpackage;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.Color;
import io.aelite.ledwall.core.animation.layer.control.LedWallControl;
import io.aelite.ledwall.core.animation.layer.control.Slider;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.animation.layer.LedWallAnimationLayer;

@LedWallAnimationLayer(name = "Strobe", description = "The LedWall lights up frequently.")
public class Strobe extends AnimationLayer {

    @LedWallControl("Color")
    private Color color;

    @LedWallControl("Frequency")
    private Slider frequency;

    private double elapsedTime;

    @Override
    public void onInit() {
        this.color = new Color(255, 255, 255);
        this.frequency = new Slider(1, 10, 1, 3);
        this.elapsedTime = 0;
    }

    @Override
    public void onUpdate(Canvas canvas, double deltaTime) {
        this.elapsedTime += deltaTime;

        if(this.elapsedTime > (1 / this.frequency.getValue())){
            canvas.fill(this.color.get());
            this.elapsedTime = 0;
        }
    }

    @Override
    public void onStop() {
    }

}
