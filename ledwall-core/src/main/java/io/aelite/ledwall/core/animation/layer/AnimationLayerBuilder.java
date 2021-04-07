package io.aelite.ledwall.core.animation.layer;

public abstract class AnimationLayerBuilder {

    private String name;
    private String description;

    public AnimationLayerBuilder(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract AnimationLayer build();

}
