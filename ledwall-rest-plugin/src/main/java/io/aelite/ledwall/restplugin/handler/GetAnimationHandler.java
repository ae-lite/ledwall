package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.restplugin.dto.AnimationDto;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GetAnimationHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        UUID uuid = UUID.fromString(context.pathParam("uuid"));
        Animation animation = LedWallApplication.INSTANCE.getAnimationController().getAnimation(uuid);

        if(animation == null){
            context.status(404);
        }else{
            context.json(new AnimationDto(animation));
        }
    }

}
