package io.aelite.ledwall.core.blendmode;

import io.aelite.ledwall.core.Canvas;

public class BlendUtil {

    public static void blend(BlendMode blendMode, Canvas bottom, Canvas top){
        for(int x = 0; x < bottom.getWidth(); x++){
            for(int y = 0; y < bottom.getHeight(); y++){

                int colorBottomn = bottom.get(x, y);
                int colorTop = top.get(x, y);
                int color = blendMode.blend(bottom.get(x, y), top.get(x, y));
                bottom.set(x, y, color);

            }
        }
    }

    public static void blend(Canvas bottom, Canvas top){
        for(int x = 0; x < bottom.getWidth(); x++){
            for(int y = 0; y < bottom.getHeight(); y++){

                int color = top.get(x, y);
                bottom.set(x, y, color);

            }
        }
    }

}
