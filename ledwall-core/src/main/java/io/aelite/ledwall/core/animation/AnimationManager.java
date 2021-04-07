package io.aelite.ledwall.core.animation;

import java.util.*;

public class AnimationManager {

    private Map<UUID, Animation> animations;

    public AnimationManager(){
        this.animations = new LinkedHashMap<UUID, Animation>();
    }

    public void addAnimation(Animation animation){
        animation.setUuid(UUID.randomUUID());
        this.animations.put(animation.getUuid(), animation);
    }

    public List<Animation> getAnimations(){
        return new ArrayList<Animation>(this.animations.values());
    }

    public Animation getAnimation(UUID uuid){
        return this.animations.get(uuid);
    }

}
