package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.layer.AnimationLayerInstantiator;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAnimationLayersHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) {
        LedWallApplication ledWallApplication = LedWallApplication.INSTANCE;
        List<AnimationLayerInstantiator> instantiators = ledWallApplication.getAnimationLayerFactory().getInstantiators();
        context.json(instantiators);
    }

}
