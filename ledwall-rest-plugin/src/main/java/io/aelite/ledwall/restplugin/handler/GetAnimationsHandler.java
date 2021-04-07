package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAnimationsHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        List<Animation> animations = LedWallApplication.INSTANCE.getAnimationManager().getAnimations();
        context.json(animations);
    }

}
