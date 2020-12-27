package io.aelite.ledwall.core;

public abstract class LedWall implements Canvas {

    private int width;
    private int height;

    public LedWall(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public abstract void show();

}
