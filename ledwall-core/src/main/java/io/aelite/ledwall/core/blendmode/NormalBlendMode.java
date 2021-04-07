package io.aelite.ledwall.core.blendmode;

import io.aelite.ledwall.core.Color;

public class NormalBlendMode implements BlendMode {

    @Override
    public int blendPixel(int bottomArgb, int topArgb) {
        //TODO fix for transparency
        int a = Color.getAlpha(topArgb);
        if(Color.getAlpha(topArgb) == 255){
            return topArgb;
        }

        int redA = Color.getRed(bottomArgb);
        int greenA = Color.getGreen(bottomArgb);
        int blueA = Color.getBlue(bottomArgb);

        int alphaB = Color.getAlpha(topArgb);
        int redB = Color.getRed(topArgb);
        int greenB = Color.getGreen(topArgb);
        int blueB = Color.getBlue(topArgb);

        double alpha = (double) alphaB / 255;
        int red     = (int) ((double) redB * alpha + (1 - alpha) * (double) redA);
        int green   = (int) ((double) greenB * alpha + (1 - alpha) * (double) greenA);
        int blue    = (int) ((double) blueB * alpha + (1 - alpha) * (double) blueA);

        // TODO: 255?
        return Color.get(255, red, green, blue);
    }

}
