package io.aelite.ledwall.core;

public class Canvas {

    private final int[][] canvas;

    public Canvas(int width, int height, int argb){
        this.canvas = new int[width][height];
        this.fill(argb);
    }

    public Canvas(int width, int height){
        this(width, height, 0x00_00_00_00);
    }

    public int getWidth() {
        return this.canvas.length;
    }

    public int getHeight() {
        return this.canvas[0].length;
    }

    public void set(int x, int y, int argb) {
        this.canvas[x][y] = argb;
    }

    public int get(int x, int y) {
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
