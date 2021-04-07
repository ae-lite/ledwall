package io.aelite.ledwall.core.animation.layer.blendmode;

public interface BlendMode {

    public static BlendMode NORMAL = new NormalBlendMode();

    public abstract int blendPixel(int bottomArgb, int topArgb);

}
