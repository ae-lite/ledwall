package io.aelite.ledwall.restplugin;

import io.aelite.ledwall.core.plugin.LedWallPlugin;
import io.aelite.ledwall.core.plugin.Plugin;
import io.aelite.ledwall.restplugin.handler.*;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LedWallPlugin
public class LedWallRestPlugin implements Plugin {

    private static final Logger logger = LoggerFactory.getLogger(LedWallRestPlugin.class);

    private Javalin javalin;

    @Override
    public void onInit() {
        logger.info("LedWallRestPlugin initialized.");
    }

    @Override
    public void onRun() {
        //TODO read port from config
        this.javalin = Javalin.create().start(8080);
        this.javalin.get("/devicetype", new GetDeviceTypeHandler());
        this.javalin.post("/shutdown", new PostShutdownHandler());
        this.javalin.get("/animations", new GetAnimationsHandler());
        this.javalin.get("/animation/:uuid", new GetAnimationHandler());
        this.javalin.post("/animation/:name", new PostAnimationHandler());
        this.javalin.put("/animation/:uuid/:layertype", new PutAnimationLayerHandler());
        this.javalin.get("/layers", new GetAnimationLayersHandler());
    }

    @Override
    public void onStop() {
        this.javalin.stop();
        logger.info("LedWallRestPlugin stopped.");
    }

}
