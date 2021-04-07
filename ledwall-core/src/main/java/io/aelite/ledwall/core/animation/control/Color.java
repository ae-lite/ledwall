package io.aelite.ledwall.core.animation.control;

public class Color extends Control {

    private int alpha;
    private int red;
    private int green;
    private int blue;

    public Color(int alpha, int red, int green, int blue){
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color(int red, int green, int blue){
        this(255, red, green, blue);
    }

    public int getARGB(){
        int result = (alpha << 24);
        result = result | (red << 16);
        result = result | (green << 8);
        result = result | (blue);
        return result;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

}
