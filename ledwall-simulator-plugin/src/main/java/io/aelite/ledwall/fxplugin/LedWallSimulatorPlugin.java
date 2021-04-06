package io.aelite.ledwall.fxplugin;

import com.google.inject.Inject;
import io.aelite.ledwall.core.Plugin;
import javafx.application.Application;
import javafx.application.Platform;

import java.util.logging.Logger;

public class LedWallSimulatorPlugin implements Plugin {

    private Logger logger;

    @Inject
    public LedWallSimulatorPlugin(Logger logger){
        this.logger = logger;
    }

    @Override
    public void onInit() {
        logger.info("LedWallSimulatorPlugin initialized.");
        new Thread(this::run);
    }

    @Override
    public void onStop() {
        Platform.exit();
        logger.info("LedWallSimulatorPlugin stopped.");
    }

    private void run() {
        Application.launch(SimulatorApplication.class);
    }

}
