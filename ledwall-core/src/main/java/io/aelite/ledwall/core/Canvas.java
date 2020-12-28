package io.aelite.ledwall.core;

import io.aelite.ledwall.core.blendmode.BlendMode;

public interface Canvas {

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void set(int x, int y, int argb);

    public abstract int get(int x, int y);

    public abstract void fill(int argb);

    public abstract void clear();

}
