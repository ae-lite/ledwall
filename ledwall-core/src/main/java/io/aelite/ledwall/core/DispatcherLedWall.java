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
    public void set(int x, int y, Color color) {
        for(LedWall observer : this.observers){
            observer.set(x, y, color);
        }
    }

    @Override
    public Color get(int x, int y) {
        //TODO fix
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void fill(Color color) {
        for(LedWall observer : this.observers){
            observer.fill(color);
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
