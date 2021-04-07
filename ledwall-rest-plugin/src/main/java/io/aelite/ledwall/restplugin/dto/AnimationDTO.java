package io.aelite.ledwall.restplugin.dto;

import io.aelite.ledwall.core.animation.Animation;

import java.util.UUID;

public class AnimationDTO {

    private UUID uuid;
    private String name;

    public AnimationDTO(Animation animation){
        this.name = animation.getName();
        this.uuid = animation.getUuid();
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

}
