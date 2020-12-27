package io.aelite.ledwall.core;

public class DefaultCanvas implements Canvas {

    Color[][] canvas;

    public DefaultCanvas(int width, int height, Color color){
        this.canvas = new Color[width][height];
        this.fill(color);
    }

    public DefaultCanvas(int width, int height){
        this(width, height, new Color(0, 0, 0));
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
    public void set(int x, int y, Color color) {
        this.canvas[x][y] = color;
    }

    @Override
    public Color get(int x, int y) {
        return this.canvas[x][y];
    }

    @Override
    public void fill(Color color) {
        for(int x = 0; x < this.canvas.length; x++){
            for(int y = 0; y < this.canvas[0].length; y++){
                this.canvas[x][y] = color;
            }
        }
    }

    @Override
    public void clear() {
        this.fill(new Color(0, 0, 0));
    }

}
