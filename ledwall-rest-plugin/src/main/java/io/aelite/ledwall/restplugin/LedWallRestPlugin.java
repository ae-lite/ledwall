package io.aelite.ledwall.restplugin;

import io.aelite.ledwall.core.plugin.Plugin;
import io.aelite.ledwall.restplugin.handler.*;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        this.javalin.get("/", new GetDeviceTypeHandler());
        this.javalin.post("/shutdown", new PostShutdownHandler());
        this.javalin.get("/animations", new GetAnimationsHandler());
        this.javalin.get("/animations/:id", new GetAnimationHandler());
        this.javalin.post("/animations/:name", new PostAnimationHandler());
    }

}
