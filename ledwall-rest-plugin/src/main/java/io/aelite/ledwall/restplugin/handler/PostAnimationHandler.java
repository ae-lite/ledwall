package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.Animation;
import io.aelite.ledwall.restplugin.dto.AnimationDto;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class PostAnimationHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        String name = context.pathParam("name");
        Animation animation = new Animation(name);
        LedWallApplication.INSTANCE.getAnimationController().addAnimation(animation);
        context.json(new AnimationDto(animation));
    }

}
