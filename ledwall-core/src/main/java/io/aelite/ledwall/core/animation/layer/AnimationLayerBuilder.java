package io.aelite.ledwall.core.animation.layer;

import java.util.UUID;
import java.util.function.Function;

public class AnimationLayerBuilder {

    private UUID uuid;
    private String name;
    private String description;
    private Function<String, AnimationLayer> constructorReference;

    public AnimationLayerBuilder(String name, String description, Function<String, AnimationLayer> constructorReference){
        this.name = name;
        this.description = description;
        this.constructorReference = constructorReference;
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

    public AnimationLayer build() {
        return this.constructorReference.apply(this.name);
    }

}
