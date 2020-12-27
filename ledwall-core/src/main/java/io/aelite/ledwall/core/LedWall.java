package io.aelite.ledwall.core;

public abstract class LedWall {

    private int width;
    private int height;

    public LedWall(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public abstract void setPixel(int x, int y, int r, int g, int b);

    public abstract void setPixels(int r, int g, int b);

    public abstract void clear();

    public abstract void show();

}
