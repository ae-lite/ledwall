package io.aelite.ledwall.core.animation;

import io.aelite.ledwall.core.BufferedCanvas;
import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.LedWall;
import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.aelite.ledwall.core.blendmode.BlendUtil;
import io.aelite.ledwall.core.blendmode.NormalBlendMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Animation {

    private UUID uuid;
    private String name;
    private LedWall ledWall;
    private List<AnimationLayer> layers;

    private Canvas clearBuffer;
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

        this.clearBuffer = new BufferedCanvas(width, height, 0xFF_00_00_00);
        this.frameBuffer = new BufferedCanvas(width, height, 0x00_00_00_00);
    }

    public void onStop(){
        this.clearBuffer = null;
        this.frameBuffer = null;
    }

    public void onUpdate(long frame){
        this.clearBuffer.fill(0xFF_00_00_00);

        for(AnimationLayer animationLayer : this.layers){
            this.frameBuffer.clear();
            try {
                animationLayer.onUpdate(this.frameBuffer, frame);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //TODO: Move blend mode to animationlayer
            BlendUtil.blend(new NormalBlendMode(), this.clearBuffer, this.frameBuffer);
        }

        BlendUtil.blend(this.ledWall, this.clearBuffer);
        this.ledWall.show();
    }

}
