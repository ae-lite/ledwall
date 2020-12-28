package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.*;
import io.aelite.ledwall.core.blendmode.BlendMode;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.animation.layer.AnimationLayerOnInit;
import io.aelite.ledwall.core.animation.layer.AnimationLayerOnStop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AnimationController {

    private static Logger logger = LoggerFactory.getLogger(AnimationController.class);

    private final List<Animation> animations;
    private Animation runningAnimation;
    private long frame;

    public AnimationController(){
        this.animations = new ArrayList<Animation>();
        this.frame = 0;
    }

    public List<Animation> getAnimations() {
        return animations;
    }

    public void addAnimation(Animation animation){
        this.animations.add(animation);
    }

    public synchronized void setRunningAnimation(Animation animation){
        this.runningAnimation.onStop();
        this.runningAnimation = animation;
        this.runningAnimation.onInit();
        this.frame = 0;
        super.notify();
    }

    //TODO refactor
    public synchronized void run(){
        LedWall ledWall = LedWallApplication.INSTANCE.getLedWall();
        int width = ledWall.getWidth();
        int height = ledWall.getHeight();
        int framerate = 60;
        int period = 1000 / framerate;
        //TODO: use delta time

        while(true){
            long start = System.currentTimeMillis();
            try {
                while(this.runningAnimation == null){
                    super.wait();
                }

                this.runningAnimation.onUpdate(this.frame);
                this.frame++;

                long elapsed = System.currentTimeMillis() - start;
                long sleep = period - elapsed;
                if(sleep > 0){
                    super.wait(sleep);
                } else {
                    //TODO better message
                    logger.warn("Elapsed: " + elapsed);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
