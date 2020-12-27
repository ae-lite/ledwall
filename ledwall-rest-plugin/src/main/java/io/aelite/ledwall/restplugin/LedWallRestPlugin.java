package io.aelite.ledwall.restplugin;

import io.aelite.ledwall.core.plugin.LedWallPlugin;
import io.aelite.ledwall.core.plugin.OnInit;
import io.aelite.ledwall.core.plugin.OnRun;
import io.aelite.ledwall.core.plugin.OnStop;
import io.aelite.ledwall.restplugin.handler.GetAnimationLayersHandler;
import io.aelite.ledwall.restplugin.handler.GetDeviceTypeHandler;
import io.aelite.ledwall.restplugin.handler.PostShutdownHandler;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LedWallPlugin
public class LedWallRestPlugin implements OnInit, OnRun, OnStop {

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
        this.javalin.get("/animationlayers", new GetAnimationLayersHandler());
    }

    @Override
    public void onStop() {
        this.javalin.stop();
        logger.info("LedWallRestPlugin stopped.");
    }

}
