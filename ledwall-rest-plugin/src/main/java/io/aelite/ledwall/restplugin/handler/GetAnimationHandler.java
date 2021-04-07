package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAnimationHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) {
        int id = Integer.parseInt(context.pathParam("id"));
        Animation animation = LedWallApplication.INSTANCE.getAnimation(id);

        if(animation == null){
            context.status(404);
        }else{
            context.json(animation);
        }
    }

}
