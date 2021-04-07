package io.aelite.ledwall.restplugin;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.animation.Animation;
import io.aelite.ledwall.core.animation.layer.AnimationLayerBuilder;
import io.aelite.ledwall.core.plugin.Plugin;
import io.aelite.ledwall.restplugin.dto.AnimationDTO;
import io.aelite.ledwall.restplugin.dto.AnimationLayerBuilderDTO;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.stream.Collectors;

public class LedWallRestPlugin implements Plugin {

    private static Logger logger = LoggerFactory.getLogger(LedWallRestPlugin.class);
    private Javalin javalin;
    private int port;

    public LedWallRestPlugin(){
        // TODO load from properties
        this.port = 8080;
    }

    @Override
    public void onInit() {
        logger.info("LedWallRestPlugin initialized");
        new Thread(this::run).start();
    }

    @Override
    public void onStop() {
        this.javalin.stop();
        logger.info("LedWallRestPlugin stopped");
    }

    private void run() {
        this.javalin = Javalin.create().start(this.port);

        this.javalin.get("/", (ctx) -> {
            ctx.result("{'deviceType': 'io.aelite.ledwall'}".replace('\'', '"'));
        });

        this.javalin.post("/shutdown", (ctx) -> {
            ctx.status(200);
            new Thread(LedWallApplication.INSTANCE::stop).start();
        });

        this.javalin.get("/layers", (ctx) -> {
            ctx.json(LedWallApplication.INSTANCE.getAnimationLayerBuilders().stream().map(AnimationLayerBuilderDTO::new).collect(Collectors.toList()));
        });

        this.javalin.get("/animations", (ctx) -> {
            ctx.json(LedWallApplication.INSTANCE.getAnimations().stream().map(AnimationDTO::new).collect(Collectors.toList()));
        });

        this.javalin.get("/animations/:uuid", (ctx) -> {
            UUID uuid = UUID.fromString(ctx.pathParam("uuid"));
            ctx.json(new AnimationDTO(LedWallApplication.INSTANCE.getAnimation(uuid)));
        });

        this.javalin.post("/animations/:name", (ctx) -> {
            Animation animation = new Animation(ctx.pathParam("name"));
            LedWallApplication.INSTANCE.addAnimation(animation);
            ctx.json(new AnimationDTO(animation));
        });

        this.javalin.put("/animations/:animationUuid/layers/:layerBuilderUuid", (ctx) -> {
            UUID animationUuid = UUID.fromString(ctx.pathParam("animationUuid"));
            UUID layerBuilderUuid = UUID.fromString(ctx.pathParam("layerBuilderUuid"));

            AnimationLayerBuilder animationLayerBuilder = LedWallApplication.INSTANCE.getAnimationLayerBuilder(layerBuilderUuid);
            Animation animation = LedWallApplication.INSTANCE.getAnimation(animationUuid);
            animation.addAnimationLayer(animationLayerBuilder.build());
        });
    }

}
