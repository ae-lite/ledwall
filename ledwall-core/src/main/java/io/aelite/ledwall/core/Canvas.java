package io.aelite.ledwall.core;

public interface Canvas {

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void set(int x, int y, Color color);

    public abstract Color get(int x, int y);

    public abstract void fill(Color color);

    public abstract void clear();

}
