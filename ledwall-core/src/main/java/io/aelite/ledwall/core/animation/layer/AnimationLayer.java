package io.aelite.ledwall.core.animation.layer;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.blendmode.BlendMode;
import io.aelite.ledwall.core.blendmode.NormalBlendMode;

import java.util.UUID;

public abstract class AnimationLayer {

    private UUID uuid;
    private BlendMode blendMode;

    public AnimationLayer(){
        this.blendMode = new NormalBlendMode();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BlendMode getBlendMode() {
        return blendMode;
    }

    public void setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
    }

    public abstract void onInit() throws Exception;

    public abstract void onUpdate(Canvas canvas, long frame) throws Exception;

    public abstract void onStop() throws Exception;

}
