package io.aelite.ledwall.core;

import io.aelite.ledwall.core.blendmode.BlendMode;

public class Canvas {

    private int[][] canvas;

    public Canvas(int width, int height, int argb){
        this.canvas = new int[width][height];
        this.fill(argb);
    }

    public Canvas(int width, int height){
        this(width, height, 0x00_00_00_00);
    }

    public Canvas(Canvas other){
        this.canvas = new int[other.getWidth()][other.getHeight()];
        this.copyFrom(other);
    }

    public void copyFrom(Canvas other){
        int newWidth = other.getWidth();
        int newHeight = other.getHeight();

        if(this.getWidth() != newWidth || this.getHeight() != newHeight){
            this.canvas = new int[newWidth][newHeight];
        }
        for(int x = 0; x < newWidth; x++){
            for(int y = 0; y < newHeight; y++){
                this.canvas[x][y] = other.canvas[x][y];
            }
        }
    }

    public void blend(Canvas top, BlendMode blendMode){
        int width = this.getWidth();
        int height = this.getHeight();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int thisArgb = this.canvas[x][y];
                int topArgb = top.canvas[x][y];
                int blendedArgb = blendMode.blendPixel(thisArgb, topArgb);
                this.canvas[x][y] = blendedArgb;
            }
        }
    }

    public int getWidth() {
        return this.canvas.length;
    }

    public int getHeight() {
        return this.canvas[0].length;
    }

    public void setPixel(int x, int y, int argb) {
        this.canvas[x][y] = argb;
    }

    public int getPixel(int x, int y) {
        return this.canvas[x][y];
    }

    public void fill(int argb) {
        for(int x = 0; x < this.canvas.length; x++){
            for(int y = 0; y < this.canvas[0].length; y++){
                this.canvas[x][y] = argb;
            }
        }
    }

    public void clear() {
        this.fill(0x00_00_00_00);
    }

}
