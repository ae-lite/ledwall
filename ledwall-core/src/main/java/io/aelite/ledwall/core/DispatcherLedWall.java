package io.aelite.ledwall.core;

import java.util.ArrayList;
import java.util.List;

public class DispatcherLedWall extends LedWall {

    private List<LedWall> observers;

    public DispatcherLedWall(int width, int height){
        super(width, height);
        this.observers =  new ArrayList<LedWall>();
    }

    public void observe(LedWall observer){
        this.observers.add(observer);
    }

    @Override
    public void setPixel(int x, int y, int r, int g, int b) {
        for(LedWall observer : this.observers){
            observer.setPixel(x, y, r, g, b);
        }
    }

    @Override
    public void setPixels(int r, int g, int b) {
        for(LedWall observer : this.observers){
            observer.setPixels(r, g, b);
        }
    }

    @Override
    public void clear() {
        for(LedWall observer : this.observers){
            observer.clear();
        }
    }

    @Override
    public void show() {
        for(LedWall observer : this.observers){
            observer.show();
        }
    }

}
