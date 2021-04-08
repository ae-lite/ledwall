package io.aelite.ledwall.core.animation.layer.blendmode;

import io.aelite.ledwall.core.util.Color;

/**
 * This implementation uses the Porter-Duff-Algorithm to blend two transparent layers.
 * @author David Adamson
 */
public class NormalBlendMode implements BlendMode {

    @Override
    public int blendPixel(int bottomArgb, int topArgb) {
        double topAlpha = (double) Color.getAlpha(topArgb) / 255;

        if(topAlpha == 1.0d){
            return topArgb;
        }

        double bottomAlpha = (double) Color.getAlpha(bottomArgb) / 255;
        double bottomRed = (double) Color.getRed(bottomArgb) / 255;
        double bottomGreen = (double) Color.getGreen(bottomArgb) / 255;
        double bottomBlue = (double) Color.getBlue(bottomArgb) / 255;

        double topRed = (double) Color.getRed(topArgb) / 255;
        double topGreen = (double) Color.getGreen(topArgb) / 255;
        double topBlue = (double) Color.getBlue(topArgb) / 255;

        double alpha = topAlpha + (1 - topAlpha) * bottomAlpha;
        double red = 1 / alpha * (topAlpha * topRed + (1 - topAlpha) * bottomAlpha * bottomRed);
        double green = 1 / alpha * (topAlpha * topGreen + (1 - topAlpha) * bottomAlpha * bottomGreen);
        double blue = 1 / alpha * (topAlpha * topBlue + (1 - topAlpha) * bottomAlpha * bottomBlue);

        return Color.getARGB(
                (int) (alpha * 255),
                (int) (red * 255),
                (int) (green * 255),
                (int) (blue * 255)
        );
    }

}
