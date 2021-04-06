package io.aelite.ledwall.core;

import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.blendmode.BlendUtil;

import java.util.ArrayList;
import java.util.List;

public class Animation {

    private String name;
    private ArrayList<AnimationLayer> animationLayers;
    private Canvas frameBuffer;

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

    public List<AnimationLayer> getAnimationLayers() {
        return animationLayers;
    }

    public void addAnimationLayer(AnimationLayer animationLayer){
        this.animationLayers.add(animationLayer);
    }

    public void onInit(){
        int width = this.ledWall.getWidth();
        int height = this.ledWall.getHeight();

        this.frameBuffer = new Canvas(width, height, 0x00_00_00_00);
        this.initLayers();
    }

    private void initLayers(){
        for(AnimationLayer animationLayer : this.layers){
            try {
                animationLayer.onInit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onStop(){
        this.stopLayers();
        this.frameBuffer = null;
    }

    private void stopLayers(){
        for(AnimationLayer animationLayer : this.layers){
            try {
                animationLayer.onStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onUpdate(Canvas canvas, double deltaTime){
        for(AnimationLayer animationLayer : this.layers){
            this.frameBuffer.fill(0x00_00_00_00);
            try {
                animationLayer.onUpdate(this.frameBuffer, deltaTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BlendUtil.blend(animationLayer.getBlendMode(), canvas, this.frameBuffer);
        }
    }

}
