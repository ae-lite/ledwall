package io.aelite.ledwall.restplugin.dto;

import io.aelite.ledwall.core.animation.layer.AnimationLayerBuilder;

import java.util.UUID;

public class AnimationLayerBuilderDTO {

    private UUID uuid;
    private String name;
    private String description;

    public AnimationLayerBuilderDTO(AnimationLayerBuilder animationLayerBuilder) {
        this.uuid = animationLayerBuilder.getUuid();
        this.name = animationLayerBuilder.getName();
        this.description = animationLayerBuilder.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
