package io.aelite.ledwall.fxplugin;

import io.aelite.ledwall.core.plugin.LedWallPlugin;
import io.aelite.ledwall.core.plugin.OnInit;
import io.aelite.ledwall.core.plugin.OnRun;
import io.aelite.ledwall.core.plugin.OnStop;
import javafx.application.Application;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LedWallPlugin
public class LedWallFxPlugin implements OnInit, OnRun, OnStop {

    private static final Logger logger = LoggerFactory.getLogger(LedWallFxPlugin.class);

    @Override
    public void onInit() {
        logger.info("LedWallFxPlugin initialized.");
    }

    @Override
    public void onRun() {
        Application.launch(FxApplication.class);
    }

    @Override
    public void onStop() {
        Platform.exit();
        logger.info("LedWallFxPlugin stopped.");
    }

}
