package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.Animation;
import io.aelite.ledwall.core.animation.layer.AnimationLayer;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PutAnimationLayerHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        UUID uuid = UUID.fromString(context.pathParam("uuid"));
        String layertype = context.pathParam("layertype");

        Animation animation = LedWallApplication.INSTANCE.getAnimationController().getAnimation(uuid);
        AnimationLayer animationLayer = LedWallApplication.INSTANCE.getAnimationLayerFactory().newAnimationLayer(layertype);

        animation.addLayer(animationLayer);
    }

}
