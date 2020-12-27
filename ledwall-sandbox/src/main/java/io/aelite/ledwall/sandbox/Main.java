package io.aelite.ledwall.sandbox;

import io.aelite.ledwall.core.LedWall;
import io.aelite.ledwall.core.LedWallApplication;

public class Main {

    public static void main(String[] args) throws Exception {
        LedWallApplication application = LedWallApplication.INSTANCE;
        application.run();

        // TODO: replace this behaviour with @AnimationLayer's
        LedWall ledWall = application.getLedWall();
        while(true){
            ledWall.setPixels(0,0,0);
            ledWall.show();
            Thread.sleep(1000);

            ledWall.setPixels(255,0,0);
            ledWall.show();
            Thread.sleep(1000);
        }
    }

}
