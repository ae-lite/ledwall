package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.BufferedCanvas;
import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.LedWall;
import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.blendmode.BlendUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Animation {

    private UUID uuid;
    private String name;
    private LedWall ledWall;
    private List<AnimationLayer> layers;

    private Canvas frameBuffer;

    public Animation(String name) {
        this.name = name;
        this.ledWall = LedWallApplication.INSTANCE.getLedWall();
        this.layers = new ArrayList<AnimationLayer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<AnimationLayer> getLayers() {
        return layers;
    }

    public void addLayer(AnimationLayer animationLayer){
        this.layers.add(animationLayer);
    }

    public void onInit(){
        int width = this.ledWall.getWidth();
        int height = this.ledWall.getHeight();

        this.frameBuffer = new BufferedCanvas(width, height, 0x00_00_00_00);
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

    public void onUpdate(Canvas canvas, long frame){
        for(AnimationLayer animationLayer : this.layers){
            this.frameBuffer.fill(0x00_00_00_00);
            try {
                animationLayer.onUpdate(this.frameBuffer, frame);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BlendUtil.blend(animationLayer.getBlendMode(), canvas, this.frameBuffer);
        }
    }

}
