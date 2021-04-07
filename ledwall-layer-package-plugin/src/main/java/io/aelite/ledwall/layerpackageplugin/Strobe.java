package io.aelite.ledwall.layerpackageplugin;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.control.Color;
import io.aelite.ledwall.core.animation.control.Slider;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.animation.layer.AnimationLayerBuilder;

public class Strobe extends AnimationLayer {

    private final Color color = new Color(255, 255, 255);
    private final Slider frequency = new Slider(1, 10, 1, 3);
    private double elapsedTime;

    public Strobe() {
        super.registerControl("Color", this.color);
        super.registerControl("Frequency", this.frequency);
    }

    @Override
    public void onInit() {
        this.elapsedTime = 0;
    }

    @Override
    public void onUpdate(Canvas canvas, double deltaTime) {
        this.elapsedTime += deltaTime;

        if(this.elapsedTime > (1 / this.frequency.getValue())){
            canvas.fill(this.color.getARGB());
            this.elapsedTime = 0;
        }
    }

    @Override
    public void onStop() {

    }

    public static class Builder extends AnimationLayerBuilder {
        public Builder() {
            super("Strobe", "The LedWall will blink in an adjustable frequency.");
        }
        @Override
        public AnimationLayer build() {
            return new Strobe();
        }
    }

}
