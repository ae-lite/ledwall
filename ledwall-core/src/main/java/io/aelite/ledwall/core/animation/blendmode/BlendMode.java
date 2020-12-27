package io.aelite.ledwall.core.animation.blendmode;

import io.aelite.ledwall.core.Canvas;

public interface BlendMode {

    public static BlendMode NORMAL = new Normal();

    public abstract Canvas blend(Canvas a, Canvas b);

}
