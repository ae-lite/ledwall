package io.aelite.ledwall.core.util;

public class Color {

    public static int getARGB(int alpha, int red, int green, int blue){
        int result = (alpha << 24);
        result = result | (red << 16);
        result = result | (green << 8);
        result = result | (blue);
        return result;
    }

    public static int getAlpha(int argb){
        int mask = 0xFF_00_00_00;
        return (argb & mask) >>> 24;
    }

    public static int getRed(int argb){
        int mask = 0x00_FF_00_00;
        return (argb & mask) >>> 16;
    }

    public static int getGreen(int argb){
        int mask = 0x00_00_FF_00;
        return (argb & mask) >>> 8;
    }

    public static int getBlue(int argb){
        int mask = 0x00_00_00_FF;
        return argb & mask;
    }

    public static int setAlpha(int argb, int alpha){
        int mask = 0x00_FF_FF_FF;
        return (argb & mask) | (alpha << 24);
    }

    public static int setRed(int argb, int red){
        int mask = 0xFF_00_FF_FF;
        return (argb & mask) | (red << 16);
    }

    public static int setGreen(int argb, int green){
        int mask = 0xFF_FF_00_FF;
        return (argb & mask) | (green << 8);
    }

    public static int setBlue(int argb, int blue){
        int mask = 0xFF_FF_FF_00;
        return (argb & mask) | blue;
    }

}
