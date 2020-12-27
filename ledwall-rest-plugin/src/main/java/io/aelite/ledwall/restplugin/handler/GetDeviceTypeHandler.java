package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWall;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetDeviceTypeHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        context.result("{\"deviceType\":\"" + LedWall.class.getName() + "\"}");
    }

}
