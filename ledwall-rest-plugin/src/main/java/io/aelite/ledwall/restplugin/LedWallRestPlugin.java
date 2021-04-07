package io.aelite.ledwall.restplugin;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.aelite.ledwall.core.plugin.Plugin;
import io.aelite.ledwall.restplugin.handler.*;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LedWallRestPlugin implements Plugin {

    private static Logger logger = LoggerFactory.getLogger(LedWallRestPlugin.class);
    private Javalin javalin;
    private int port;

    @Inject
    public LedWallRestPlugin(@Named("ledwall.rest.port") int port){
        this.port = port;
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
        this.javalin.get("/devicetype", new GetDeviceTypeHandler());
        this.javalin.post("/shutdown", new PostShutdownHandler());
        this.javalin.get("/animations", new GetAnimationsHandler());
        this.javalin.get("/animations/:index", new GetAnimationHandler());
        this.javalin.post("/animations/:name", new PostAnimationHandler());
    }

}
