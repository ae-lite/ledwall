package io.aelite.ledwall.core.plugin;

public interface Plugin {

    public abstract void onInit() throws Exception;

    public abstract void onRun() throws Exception;

    public abstract void onStop() throws Exception;

}
