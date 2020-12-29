package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.*;
import io.aelite.ledwall.core.blendmode.BlendUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AnimationController {

    private static Logger logger = LoggerFactory.getLogger(AnimationController.class);

    private final Map<UUID, Animation> animations;
    private Animation runningAnimation;
    private long frame;

    public AnimationController(){
        this.animations = new HashMap<UUID, Animation>();
        this.frame = 0;
    }

    public List<Animation> getAnimations() {
        return new ArrayList<Animation>(this.animations.values());
    }

    public void addAnimation(Animation animation){
        animation.setUuid(UUID.randomUUID());
        this.animations.put(animation.getUuid(), animation);
    }

    public Animation getAnimation(UUID uuid){
        return this.animations.get(uuid);
    }

    public synchronized void runAnimation(Animation animation){
        this.stop();
        this.runningAnimation = animation;
        this.runningAnimation.onInit();
        this.frame = 0;
        super.notify();
    }

    public synchronized void stop(){
        if(this.runningAnimation != null){
            this.runningAnimation.onStop();
            this.runningAnimation = null;
        }
    }

    //TODO refactor
    public synchronized void run(){
        LedWall ledWall = LedWallApplication.INSTANCE.getLedWall();
        Canvas clearBuffer = new BufferedCanvas(ledWall.getWidth(), ledWall.getHeight(), 0xFF_00_00_00);
        //TODO: load framerate from config
        int framerate = 60;
        int period = 1000 / framerate;
        //TODO: use delta time

        while(true){
            long start = System.currentTimeMillis();
            try {
                while(this.runningAnimation == null){
                    super.wait();
                }

                clearBuffer.fill(0xFF_00_00_00);
                this.runningAnimation.onUpdate(clearBuffer, this.frame);
                BlendUtil.blend(ledWall, clearBuffer);
                ledWall.show();
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
