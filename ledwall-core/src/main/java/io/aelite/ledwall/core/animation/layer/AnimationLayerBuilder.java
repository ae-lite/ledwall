package io.aelite.ledwall.core.animation.layer;

import java.util.UUID;

public abstract class AnimationLayerBuilder {

    private UUID uuid;
    private String name;
    private String description;

    public AnimationLayerBuilder(String name, String description){
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public abstract AnimationLayer build();

}
