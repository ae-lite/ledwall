package io.aelite.ledwall.restplugin.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetDeviceTypeHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) {
        context.result("{\"deviceType\":\"io.aelite.ledwall\"}");
    }

}
