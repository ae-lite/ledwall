package io.aelite.ledwall.restplugin.dto;

import io.aelite.ledwall.core.animation.control.Control;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;

import java.util.Map;
import java.util.UUID;

public class AnimationLayerDTO {

    private UUID uuid;
    private String name;
    private Map<String, Control> controls;

    public AnimationLayerDTO(AnimationLayer animationLayer){
        this.uuid = animationLayer.getUuid();
        this.name = animationLayer.getName();
        this.controls = animationLayer.getControls();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Control> getControls() {
        return controls;
    }

}
