package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.control.Control;
import io.aelite.ledwall.core.animation.control.Select;
import io.aelite.ledwall.core.blendmode.BlendMode;

import java.util.*;

public abstract class AnimationLayer {

    private Map<String, Control> controls;
    private Select<BlendMode> blendModes;

    public AnimationLayer(Set<BlendMode> blendModes){
        this.controls = new LinkedHashMap<String, Control>();
        this.blendModes = new Select<BlendMode>(blendModes);
        this.registerControl("Blend Mode", this.blendModes);
    }

    public abstract void onInit() throws Exception;

    public abstract void onUpdate(Canvas canvas, double deltaTime) throws Exception;

    public abstract void onStop() throws Exception;

    public void registerControl(String name, Control control){
        this.controls.put(name, control);
    }

    public BlendMode getBlendMode(){
        return this.blendModes.getSelection();
    }

}
