package io.aelite.ledwall.core.animation.layer;

import io.aelite.ledwall.core.Canvas;

import java.util.UUID;

public abstract class AnimationLayer {

    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public abstract void onUpdate(Canvas canvas, long frame) throws Exception;

}
