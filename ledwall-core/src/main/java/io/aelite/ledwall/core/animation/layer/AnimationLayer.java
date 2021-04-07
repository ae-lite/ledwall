package io.aelite.ledwall.core.animation.layer;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.control.Control;
import io.aelite.ledwall.core.animation.control.Select;
import io.aelite.ledwall.core.animation.layer.blendmode.BlendMode;

import java.util.*;

public abstract class AnimationLayer {

    private UUID uuid;
    private String name;
    private Map<String, Control> controls;
    private Select<BlendMode> blendModes;

    public AnimationLayer(String name){
        this.name = name;
        this.controls = new LinkedHashMap<String, Control>();
        this.blendModes = new Select<BlendMode>(
                BlendMode.NORMAL
        );
        this.registerControl("Blend Mode", this.blendModes);
    }

    public abstract void onInit() throws Exception;

    public abstract void onUpdate(Canvas canvas, double deltaTime) throws Exception;

    public abstract void onStop() throws Exception;

    public void registerControl(String name, Control control){
        this.controls.put(name, control);
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

    public void setName(String name) {
        this.name = name;
    }

    public BlendMode getBlendMode(){
        return this.blendModes.getSelection();
    }

}
