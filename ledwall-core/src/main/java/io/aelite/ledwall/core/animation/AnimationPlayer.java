package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.Canvas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AnimationPlayer {

    private static final Logger logger = LoggerFactory.getLogger(AnimationPlayer.class);

    private int maxFps;
    private int width;
    private int height;
    private Animation runningAnimation;
    private List<Consumer<Canvas>> frameUpdateCallbacks;

    public AnimationPlayer(int maxFps, int width, int height){
        this.maxFps = maxFps;
        this.width = width;
        this.height = height;
        this.runningAnimation = null;
        this.frameUpdateCallbacks = new ArrayList<Consumer<Canvas>>();
    }

    public synchronized void play(Animation animation){
        this.stop();
        this.runningAnimation = animation;
        this.runningAnimation.onInit(this.width, this.height);
        super.notify();
    }

    public synchronized void stop(){
        if(this.runningAnimation != null){
            this.runningAnimation.onStop();
            this.runningAnimation = null;
        }
    }

    public void startRenderer(){
        new Thread(this::runRenderLoop, "LedWall Render Thread").start();
    }

    private synchronized void runRenderLoop(){
        int period = 1000 / this.maxFps;
        long millis_current = System.currentTimeMillis();

        while(true){
            long millis_last_frame = millis_current;
            millis_current = System.currentTimeMillis();
            double deltaTime = (double) (millis_current - millis_last_frame) / 1000;

            try {
                while(this.runningAnimation == null){
                    super.wait();
                }

                Canvas frame = this.runningAnimation.onUpdate(deltaTime);
                this.publishFrame(frame);

                long elapsed = System.currentTimeMillis() - millis_current;
                long sleep = period - elapsed;
                if(sleep > 0){
                    super.wait(sleep);
                }else{
                    logger.warn("Detected frame drop. Frame render duration: " + elapsed + "ms");
                    super.wait(5);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onFrameUpdate(Consumer<Canvas> frameUpdateCallback) {
        this.frameUpdateCallbacks.add(frameUpdateCallback);
    }

    private void publishFrame(Canvas canvas){
        for(Consumer<Canvas> consumer : this.frameUpdateCallbacks){
            consumer.accept(canvas);
        }
    }

}
