package io.aelite.ledwall.core.animation.layer;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.control.Control;
import io.aelite.ledwall.core.animation.control.Select;
import io.aelite.ledwall.core.animation.control.Slider;
import io.aelite.ledwall.core.animation.layer.blendmode.BlendMode;

import java.util.*;

public abstract class AnimationLayer {

    private UUID uuid;
    private String name;
    private Map<String, Control> controls;

    private Select<String, BlendMode> blendModes;
    private Slider opacity;

    public AnimationLayer(String name){
        this.name = name;
        this.controls = new LinkedHashMap<String, Control>();

        this.blendModes = new Select<String, BlendMode>();
        this.blendModes.put("Normal", BlendMode.NORMAL);
        this.registerControl("Blend Mode", this.blendModes);

        this.opacity = new Slider(0, 100, 1, 100);
        this.registerControl("Opacity", this.opacity);
    }

    public abstract void onInit() throws Exception;

    public abstract void onUpdate(Canvas canvas, double deltaTime) throws Exception;

    public abstract void onStop() throws Exception;

    public void registerControl(String name, Control control){
        this.controls.put(name, control);
    }

    public Map<String, Control> getControls() {
        return controls;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public BlendMode getBlendMode(){
        return this.blendModes.getSelectedValue();
    }

}
