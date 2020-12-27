package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.animation.layer.AnimationLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Animation {

    private UUID uuid;
    private String name;
    private List<AnimationLayer> layers;

    public Animation(String name) {
        this.name = name;
        this.layers = new ArrayList<AnimationLayer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<AnimationLayer> getLayers() {
        return layers;
    }

    public void addLayer(AnimationLayer animationLayer){
        this.layers.add(animationLayer);
    }

}
