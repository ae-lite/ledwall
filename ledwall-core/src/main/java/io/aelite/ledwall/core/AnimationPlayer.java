package io.aelite.ledwall.core;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AnimationPlayer {

    private int maxFps;
    private int width;
    private int height;

    private Animation runningAnimation;

    private List<Consumer<Canvas>> frameUpdateSubscribers;

    @Inject
    public AnimationPlayer(
        @Named("ledwall.core.maxfps") int maxFps,
        @Named("ledwall.core.width") int width,
        @Named("ledwall.core.height") int height
    ){
        this.maxFps = maxFps;
        this.width = width;
        this.height= height;

        this.frameUpdateSubscribers = new ArrayList<Consumer<Canvas>>();
    }

    public void play(Animation animation){
        this.stop();
        this.runningAnimation = animation;
        this.runningAnimation.onInit();
        super.notify();
    }

    public void stop(){
        if(this.runningAnimation != null){
            this.runningAnimation.onStop();
            this.runningAnimation = null;
        }
    }

    public void runRenderLoop(){
        Canvas frame = new Canvas(this.width, this.height);
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

                frame.fill(0xFF_00_00_00);
                this.runningAnimation.onUpdate(frame, deltaTime);
                this.publishFrame(frame);

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

    public void subscribeToFrameUpdates(Consumer<Canvas> consumer){
        this.frameUpdateSubscribers.add(consumer);
    }

    private void publishFrame(Canvas canvas){
        for(Consumer<Canvas> consumer : this.frameUpdateSubscribers){
            consumer.accept(canvas);
        }
    }

}
