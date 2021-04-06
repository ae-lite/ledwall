package io.aelite.ledwall.core;

public interface Plugin {

    public abstract void onInit() throws Exception;

    public abstract void onStop() throws Exception;

}
