package io.aelite.ledwall.core;

public class Color {

    private int argb;

    public Color(int argb){
        this.argb = argb;
    }

    public Color(int alpha, int red, int green, int blue){
        this.argb = Color.get(alpha, red, green, blue);
    }

    public Color(int red, int green, int blue){
        this(255, red, green, blue);
    }

    public int get(){
        return this.argb;
    }

    public static int get(int alpha, int red, int green, int blue){
        int result = (alpha << 24);
        result = result | (red << 16);
        result = result | (green << 8);
        result = result | (blue);
        return result;
    }

    public static int getAlpha(int argb){
        int mask = 0xFF000000;
        return (argb & mask) >> 24;
    }

    public static int getRed(int argb){
        int mask = 0x00FF0000;
        return (argb & mask) >> 16;
    }

    public static int getGreen(int argb){
        int mask = 0x0000FF00;
        return (argb & mask) >> 8;
    }

    public static int getBlue(int argb){
        int mask = 0x000000FF;
        return argb & mask;
    }

}
