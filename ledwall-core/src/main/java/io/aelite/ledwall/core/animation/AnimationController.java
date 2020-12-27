package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.*;
import io.aelite.ledwall.core.animation.blendmode.BlendMode;
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
        this.stopRunningAnimation();
        this.runningAnimation = animation;
        this.startRunningAnimation();
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

        while(true){
            long start = System.currentTimeMillis();
            try {
                while(this.runningAnimation == null){
                    super.wait();
                }

                Canvas background = new DefaultCanvas(width, height);
                for(AnimationLayer animationLayer : this.runningAnimation.getLayers()){
                    Canvas layer = new DefaultCanvas(width, height, new Color(0, 0, 0, 0));
                    animationLayer.onUpdate(layer, this.frame);
                    background = BlendMode.NORMAL.blend(background, layer);
                }

                for(int x = 0; x < width; x++){
                    for(int y = 0; y < height; y++){
                        ledWall.set(x, y, background.get(x, y));
                    }
                }

                ledWall.show();
                this.frame++;

                long elapsed = System.currentTimeMillis() - start;
                long sleep = period - elapsed;
                if(sleep > 0){
                    super.wait(sleep);
                } else {
                    logger.warn("Elapsed: " + elapsed);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startRunningAnimation(){
        if(this.runningAnimation instanceof AnimationLayerOnInit){
            try {
                ((AnimationLayerOnInit) this.runningAnimation).onInit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void stopRunningAnimation(){
        if(this.runningAnimation instanceof AnimationLayerOnStop){
            try {
                ((AnimationLayerOnStop) this.runningAnimation).onStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.runningAnimation = null;
    }

}
