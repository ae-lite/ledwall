package io.aelite.ledwall.core.plugin;

import io.aelite.ledwall.core.LedWallApplication;

public interface Plugin {

    public abstract void onInit(LedWallApplication application) throws Exception;

    public abstract void onStop() throws Exception;

}
