package io.aelite.ledwall.core;

import java.util.ArrayList;
import java.util.List;

public class DispatcherLedWall extends BufferedLedWall {

    private List<LedWall> observers;

    public DispatcherLedWall(int width, int height){
        super(width, height);
        this.observers =  new ArrayList<LedWall>();
    }

    public void observe(LedWall observer){
        this.observers.add(observer);
    }

    @Override
    public void set(int x, int y, int argb) {
        super.set(x, y, argb);
        for(LedWall observer : this.observers){
            observer.set(x, y, argb);
        }
    }

    @Override
    public int get(int x, int y) {
        return super.get(x, y);
    }

    @Override
    public void fill(int argb) {
        super.fill(argb);
        for(LedWall observer : this.observers){
            observer.fill(argb);
        }
    }

    @Override
    public void clear() {
        super.clear();
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
