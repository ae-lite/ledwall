package io.aelite.ledwall.core.blendmode;

import io.aelite.ledwall.core.Color;

public class NormalBlendMode implements BlendMode {

    @Override
    public int blend(int bottom, int top) {
        int a = Color.getAlpha(top);
        if(Color.getAlpha(top) == 255){
            return top;
        }

        int redA = Color.getRed(bottom);
        int greenA = Color.getGreen(bottom);
        int blueA = Color.getBlue(bottom);

        int alphaB = Color.getAlpha(top);
        int redB = Color.getRed(top);
        int greenB = Color.getGreen(top);
        int blueB = Color.getBlue(top);

        double alpha = (double) alphaB / 255;
        int red     = (int) ((double) redB * alpha + (1 - alpha) * (double) redA);
        int green   = (int) ((double) greenB * alpha + (1 - alpha) * (double) greenA);
        int blue    = (int) ((double) blueB * alpha + (1 - alpha) * (double) blueA);

        return Color.get(255, red, green, blue);
    }

}
