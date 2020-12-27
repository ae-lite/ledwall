package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.core.animation.layer.AnimationLayerFactory;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAnimationLayersHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) {
        LedWallApplication ledWallApplication = LedWallApplication.INSTANCE;
        //TODO remove
        Animation a = ledWallApplication.getAnimationController().getAnimations().get(0);
        ledWallApplication.getAnimationController().setRunningAnimation(a);
        context.result("ok");

        //TODO List<AnimationLayerFactory> animationLayerFactories = ledWallApplication.getAnimationLayerController().getAnimationLayerFactories();

        //TODO context.json(animationLayerFactories);
    }

}
