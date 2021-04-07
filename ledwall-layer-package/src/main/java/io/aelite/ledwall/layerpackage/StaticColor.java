package io.aelite.ledwall.layerpackage;

import com.google.inject.Inject;
import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.AnimationLayer;
import io.aelite.ledwall.core.animation.control.Color;
import io.aelite.ledwall.core.blendmode.BlendMode;

import java.util.Set;

public class StaticColor extends AnimationLayer {

    private final Color color = new Color(255, 255, 255);

    @Inject
    public StaticColor(Set<BlendMode> blendModes){
        super(blendModes);
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

}
