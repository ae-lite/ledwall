package io.aelite.ledwall.core.animation;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class AnimationManager {

    private List<Animation> animations;
    private AnimationPlayer animationPlayer;

    @Inject
    public AnimationManager(AnimationPlayer animationPlayer){
        this.animations = new ArrayList<Animation>();
        this.animationPlayer = animationPlayer;
    }

    public void addAnimation(Animation animation){
        this.animations.add(animation);
    }

    public List<Animation> getAnimations(){
        return this.animations;
    }

    public Animation getAnimation(int index){
        return this.animations.get(index);
    }

    public AnimationPlayer getAnimationPlayer(){
        return this.animationPlayer;
    }

}
