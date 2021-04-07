package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class PostShutdownHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) {
        context.status(200);
        new Thread(LedWallApplication.INSTANCE::stop).start();
    }

}
