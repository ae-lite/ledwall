package io.aelite.ledwall.core;

public class BufferedCanvas implements Canvas {

    private final int[][] canvas;

    public BufferedCanvas(int width, int height, int argb){
        this.canvas = new int[width][height];

        for(int x = 0; x < this.canvas.length; x++){
            for(int y = 0; y < this.canvas[0].length; y++){
                this.canvas[x][y] = argb;
            }
        }
    }

    public BufferedCanvas(int width, int height){
        this(width, height, 0xFF000000);
    }

    @Override
    public int getWidth() {
        return this.canvas.length;
    }

    @Override
    public int getHeight() {
        return this.canvas[0].length;
    }

    @Override
    public void set(int x, int y, int argb) {
        this.canvas[x][y] = argb;
    }

    @Override
    public int get(int x, int y) {
        return this.canvas[x][y];
    }

    @Override
    public void fill(int argb) {
        for(int x = 0; x < this.canvas.length; x++){
            for(int y = 0; y < this.canvas[0].length; y++){
                this.canvas[x][y] = argb;
            }
        }
    }

    @Override
    public void clear() {
        this.fill(0xFF000000);
    }

}
