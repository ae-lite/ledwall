package io.aelite.ledwall.core.animation.blendmode;

import io.aelite.ledwall.core.Canvas;

public class Normal implements BlendMode {

    @Override
    public Canvas blend(Canvas a, Canvas b) {
        //TODO fix
        int width = a.getWidth();
        int height = a.getHeight();

        /*for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int colorA = a.get(x, y);
                int colorB = b.get(x, y);

                double alpha = (double) colorB.getAlpha() / 255;
                int red     = (int) ((double) colorB.getRed() * alpha + (1 - alpha) * (double) colorA.getRed());
                int green   = (int) ((double) colorB.getGreen() * alpha + (1 - alpha) * (double) colorA.getGreen());
                int blue    = (int) ((double) colorB.getBlue() * alpha + (1 - alpha) * (double) colorA.getBlue());

                a.set(x, y, new Color(red, green, blue));
            }
        }*/
        return b;
    }

}
