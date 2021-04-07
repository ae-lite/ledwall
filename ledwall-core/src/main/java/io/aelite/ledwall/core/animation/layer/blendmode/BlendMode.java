package io.aelite.ledwall.core.animation.layer.blendmode;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface BlendMode {

    public static BlendMode NORMAL = new NormalBlendMode();

    public abstract int blendPixel(int bottomArgb, int topArgb);

}
