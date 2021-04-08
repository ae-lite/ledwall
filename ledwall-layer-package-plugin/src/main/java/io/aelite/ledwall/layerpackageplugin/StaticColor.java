package io.aelite.ledwall.layerpackageplugin;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.animation.control.Color;

public class StaticColor extends AnimationLayer {

    private final Color color = new Color("Color", 255, 255, 255);

    public StaticColor(String name){
        super(name);
        super.registerControl(this.color);
    }

    @Override public void onInit() {}

    @Override public void onUpdate(Canvas canvas, double deltaTime) {
        canvas.fill(this.color.getARGB());
    }

    @Override public void onStop() {}

}
