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

    public AnimationController(){
        this.animations = new HashMap<UUID, Animation>();
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
        super.notify();
    }

    public synchronized void stop(){
        if(this.runningAnimation != null){
            this.runningAnimation.onStop();
            this.runningAnimation = null;
        }
    }

    //TODO clean up
    public synchronized void run(){
        LedWall ledWall = LedWallApplication.INSTANCE.getLedWall();
        Canvas clearBuffer = new BufferedCanvas(ledWall.getWidth(), ledWall.getHeight(), 0xFF_00_00_00);

        //TODO load fps from config
        int fps = 30;
        int period = 1000 / fps;

        long millis_current = System.currentTimeMillis();

        while(true){
            long millis_last_frame = millis_current;
            millis_current = System.currentTimeMillis();
            double deltaTime = (double) (millis_current - millis_last_frame) / 1000;

            try {
                while(this.runningAnimation == null){
                    super.wait();
                }

                clearBuffer.fill(0xFF_00_00_00);
                this.runningAnimation.onUpdate(clearBuffer, deltaTime);
                BlendUtil.blend(ledWall, clearBuffer);
                ledWall.show();

                long elapsed = System.currentTimeMillis() - millis_current;
                long sleep = period - elapsed;
                if(sleep > 0){
                    super.wait(sleep);
                }else{
                    super.wait(5);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
