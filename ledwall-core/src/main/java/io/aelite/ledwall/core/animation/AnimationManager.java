package io.aelite.ledwall.core.animation;

import java.util.ArrayList;
import java.util.List;

public class AnimationManager {

    private List<Animation> animations;

    public AnimationManager(){
        this.animations = new ArrayList<Animation>();
    }

    public void addAnimation(Animation animation){
        this.animations.add(animation);
    }

    public List<Animation> getAnimations(){
        return this.animations;
    }

    public Animation getAnimation(int id){
        return this.animations.get(id);
    }

}
