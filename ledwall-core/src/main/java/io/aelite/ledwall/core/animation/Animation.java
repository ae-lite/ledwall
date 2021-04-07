package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Animation {

    private String name;
    private UUID uuid;
    private ArrayList<AnimationLayer> animationLayers;

    private Canvas backgroundFrameBuffer;
    private Canvas layerFrameBuffer;

    public Animation(String name) {
        this.name = name;
        this.animationLayers = new ArrayList<AnimationLayer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid(){
        return this.uuid;
    }

    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    public List<AnimationLayer> getAnimationLayers() {
        return animationLayers;
    }

    public void addAnimationLayer(AnimationLayer animationLayer){
        this.animationLayers.add(animationLayer);
    }

    public void onInit(int width, int height){
        this.backgroundFrameBuffer = new Canvas(width, height, 0xFF_00_00_00);
        this.layerFrameBuffer = new Canvas(width, height, 0x00_00_00_00);

        for(AnimationLayer animationLayer : this.animationLayers){
            try {
                animationLayer.onInit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Canvas onUpdate(double deltaTime){
        this.backgroundFrameBuffer.fill(0xFF_00_00_00);

        for(AnimationLayer animationLayer : this.animationLayers){
            this.layerFrameBuffer.fill(0x00_00_00_00);
            try {
                animationLayer.onUpdate(this.layerFrameBuffer, deltaTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.backgroundFrameBuffer.blend(this.layerFrameBuffer, animationLayer.getBlendMode());
        }
        return this.backgroundFrameBuffer;
    }

    public void onStop(){
        for(AnimationLayer animationLayer : this.animationLayers){
            try {
                animationLayer.onStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.backgroundFrameBuffer = null;
    }

}
