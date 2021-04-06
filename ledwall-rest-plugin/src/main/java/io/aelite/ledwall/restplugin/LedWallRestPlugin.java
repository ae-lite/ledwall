package io.aelite.ledwall.restplugin;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.aelite.ledwall.core.Plugin;
import io.aelite.ledwall.restplugin.handler.*;
import io.javalin.Javalin;
import org.slf4j.LoggerFactory;

public class LedWallRestPlugin implements Plugin {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(LedWallRestPlugin.class);
    private Javalin javalin;
    private int port;

    @Inject
    public LedWallRestPlugin(@Named("ledwall.rest.port") int port){
        this.port = port;
    }

    @Override
    public void onInit() {
        logger.info("LedWallRestPlugin initialized.");
        new Thread(this::run).start();
    }

    @Override
    public void onStop() {
        this.javalin.stop();
        logger.info("LedWallRestPlugin stopped.");
    }

    private void run() {
        this.javalin = Javalin.create().start(this.port);
        this.javalin.get("/devicetype", new GetDeviceTypeHandler());
        this.javalin.post("/shutdown", new PostShutdownHandler());
        this.javalin.get("/animations", new GetAnimationsHandler());
        this.javalin.get("/animation/:uuid", new GetAnimationHandler());
        this.javalin.post("/animation/:name", new PostAnimationHandler());
        this.javalin.put("/animation/:uuid/:layertype", new PutAnimationLayerHandler());
        this.javalin.get("/layers", new GetAnimationLayersHandler());
    }

}
