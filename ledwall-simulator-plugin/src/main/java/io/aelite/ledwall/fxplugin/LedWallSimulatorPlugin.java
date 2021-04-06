package io.aelite.ledwall.fxplugin;

import io.aelite.ledwall.core.Plugin;
import javafx.application.Application;
import javafx.application.Platform;
import org.slf4j.LoggerFactory;

public class LedWallSimulatorPlugin implements Plugin {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LedWallSimulatorPlugin.class);

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
