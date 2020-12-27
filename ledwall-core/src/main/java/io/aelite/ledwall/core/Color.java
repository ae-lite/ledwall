package io.aelite.ledwall.core;

public class Color {

    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    public Color(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 255;
    }

    public Color red(int red){
        return new Color(red, this.green, this.blue, this.alpha);
    }

    public Color g(int green){
        return new Color(this.red, green, this.blue, this.alpha);
    }

    public Color b(int blue){
        return new Color(this.red, this.green, blue, this.alpha);
    }

    public Color a(int alpha){
        return new Color(this.red, this.green, this.blue, alpha);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

}
