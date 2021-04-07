package io.aelite.ledwall.core.animation.layer;

import java.util.ArrayList;
import java.util.List;

public class AnimationLayerManager {

    private List<AnimationLayerBuilder> animationLayerBuilders;

    public AnimationLayerManager(){
        this.animationLayerBuilders = new ArrayList<AnimationLayerBuilder>();
    }

    public void addAnimationLayerBuilder(AnimationLayerBuilder builder) {
        this.animationLayerBuilders.add(builder);
    }

}
