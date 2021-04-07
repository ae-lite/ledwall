package io.aelite.ledwall.layerpackageplugin;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.animation.control.Color;
import io.aelite.ledwall.core.animation.layer.AnimationLayerBuilder;

public class StaticColor extends AnimationLayer {

    private final Color color = new Color(255, 255, 255);

    public StaticColor(){
        super.registerControl("Color", this.color);
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onUpdate(Canvas canvas, double deltaTime) {
        canvas.fill(this.color.getARGB());
    }

    @Override
    public void onStop() {

    }

    public static class Builder extends AnimationLayerBuilder {
        public Builder() {
            super("Static Color", "Lights up the LedWall in one color.");
        }
        @Override
        public AnimationLayer build() {
            return new StaticColor();
        }
    }

}
