package io.aelite.ledwall.restplugin.dto;

import io.aelite.ledwall.core.animation.control.Control;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;

import java.util.Map;

public class AnimationLayerDTO {

    private String name;
    private Map<String, Control> controls;

    public AnimationLayerDTO(AnimationLayer animationLayer){
        this.name = animationLayer.getName();
        this.controls = animationLayer.getControls();
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Control> getControls() {
        return controls;
    }

}
