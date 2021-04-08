package io.aelite.ledwall.layerpackageplugin;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.control.Color;
import io.aelite.ledwall.core.animation.control.Slider;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;

public class Strobe extends AnimationLayer {

    private final Color color = new Color("Color", 255, 255, 255);
    private final Slider frequency = new Slider("Frequency", 1, 10, 1, 3);

    private double elapsedTime;

    public Strobe(String name) {
        super(name);
        super.registerControl(this.color);
        super.registerControl(this.frequency);
    }

    @Override public void onInit() {
        this.elapsedTime = 0;
    }

    @Override public void onUpdate(Canvas canvas, double deltaTime) {
        this.elapsedTime += deltaTime;

        if(this.elapsedTime > (1 / this.frequency.getValue())){
            canvas.fill(this.color.getARGB());
            this.elapsedTime = 0;
        }
    }

    @Override public void onStop() {}

}
