package io.aelite.ledwall.core.animation.layer;

import java.util.*;

public class AnimationLayerManager {

    private Map<UUID, AnimationLayerBuilder> animationLayerBuilders;

    public AnimationLayerManager(){
        this.animationLayerBuilders = new LinkedHashMap<UUID, AnimationLayerBuilder>();
    }

    public void addAnimationLayerBuilder(AnimationLayerBuilder builder) {
        builder.setUuid(UUID.randomUUID());
        this.animationLayerBuilders.put(builder.getUuid(), builder);
    }

    public List<AnimationLayerBuilder> getAnimationLayerBuilders() {
        return new ArrayList<>(this.animationLayerBuilders.values());
    }

    public AnimationLayerBuilder getAnimationLayerBuilder(UUID uuid) {
        return this.animationLayerBuilders.get(uuid);
    }
}
