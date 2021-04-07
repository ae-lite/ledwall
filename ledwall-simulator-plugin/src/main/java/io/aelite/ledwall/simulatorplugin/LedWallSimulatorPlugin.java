package io.aelite.ledwall.simulatorplugin;

import io.aelite.ledwall.core.plugin.Plugin;
import javafx.application.Application;
import javafx.application.Platform;
import org.slf4j.LoggerFactory;

public class LedWallSimulatorPlugin implements Plugin {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LedWallSimulatorPlugin.class);

    @Override
    public void onInit() {
        logger.info("LedWallSimulatorPlugin initialized");
        new Thread(() -> Application.launch(SimulatorApplication.class)).start();
    }

    @Override
    public void onStop() {
        Platform.exit();
        logger.info("LedWallSimulatorPlugin stopped");
    }

}
