package io.aelite.ledwall.core.animation.layer;

import io.aelite.ledwall.core.LedWall;

public interface AnimationLayerOnUpdate {

    public abstract void onUpdate(long frame, LedWall canvas) throws Exception;

}
