package io.aelite.ledwall.restplugin;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.core.animation.layer.AnimationLayerBuilder;
import io.aelite.ledwall.core.plugin.Plugin;
import io.aelite.ledwall.restplugin.dto.AnimationDTO;
import io.aelite.ledwall.restplugin.dto.AnimationLayerBuilderDTO;
import io.aelite.ledwall.restplugin.dto.DetailedAnimationDTO;
import io.aelite.ledwall.restplugin.dto.DeviceTypeDTO;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.stream.Collectors;

public class LedWallRestPlugin implements Plugin {

    private static Logger logger = LoggerFactory.getLogger(LedWallRestPlugin.class);

    private LedWallApplication application;
    private Javalin javalin;

    @Override
    public void onInit(LedWallApplication application) {
        this.application = application;

        this.application.properties().setDefault("io.aelite.ledwall.restplugin.port", 8080);

        new Thread(this::run).start();
        logger.info("LedWallRestPlugin initialized");
    }

    @Override
    public void onStop() {
        this.javalin.stop();
        logger.info("LedWallRestPlugin stopped");
    }

    private void run() {
        this.javalin = Javalin.create().start(this.application.properties().getInt("io.aelite.ledwall.restplugin.port"));

        this.javalin.get("/", (ctx) -> {
            ctx.json(new DeviceTypeDTO());
        });

        this.javalin.post("/shutdown", (ctx) -> {
            ctx.status(200);
            new Thread(this.application::stop).start();
        });

        this.javalin.get("/layers", (ctx) -> {
            ctx.json(this.application.getAnimationLayerBuilders().stream().map(AnimationLayerBuilderDTO::new).collect(Collectors.toList()));
        });

        this.javalin.get("/animations", (ctx) -> {
            ctx.json(this.application.getAnimations().stream().map(AnimationDTO::new).collect(Collectors.toList()));
        });

        this.javalin.get("/animations/:uuid", (ctx) -> {
            UUID uuid = UUID.fromString(ctx.pathParam("uuid"));
            ctx.json(new DetailedAnimationDTO(this.application.getAnimation(uuid)));
        });

        this.javalin.post("/animations/:name", (ctx) -> {
            Animation animation = new Animation(ctx.pathParam("name"));
            this.application.addAnimation(animation);
            ctx.json(new DetailedAnimationDTO(animation));
        });

        this.javalin.put("/animations/:animationUuid/layers/:layerBuilderUuid", (ctx) -> {
            UUID animationUuid = UUID.fromString(ctx.pathParam("animationUuid"));
            UUID layerBuilderUuid = UUID.fromString(ctx.pathParam("layerBuilderUuid"));

            AnimationLayerBuilder animationLayerBuilder = this.application.getAnimationLayerBuilder(layerBuilderUuid);
            Animation animation = this.application.getAnimation(animationUuid);
            animation.addAnimationLayer(animationLayerBuilder.build());

            ctx.json(new DetailedAnimationDTO(animation));
        });
    }

}
