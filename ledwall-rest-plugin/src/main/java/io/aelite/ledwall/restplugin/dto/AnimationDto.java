package io.aelite.ledwall.restplugin.dto;

import io.aelite.ledwall.core.animation.Animation;

import java.util.UUID;

public class AnimationDto {

    private UUID uuid;
    private String name;

    public AnimationDto(Animation animation){
        this.uuid = animation.getUuid();
        this.name = animation.getName();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
