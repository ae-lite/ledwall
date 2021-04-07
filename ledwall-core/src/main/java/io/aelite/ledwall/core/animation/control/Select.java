package io.aelite.ledwall.core.animation.control;

import java.util.*;

public class Select<T> extends Control {

    private List<T> elements;
    private int selectedIndex;

    public Select(Iterable<T> elements) {
        this.elements = new ArrayList<T>();
        for (T element : elements) {
            this.elements.add(element);
        }
    }

    public Select(String name, T... elements) {
        this(Arrays.asList(elements));
    }

    public List<T> getElements() {
        return this.elements;
    }

    public void setSelectedIndex(int index){
        this.selectedIndex = index;
    }

    public int getSelectedIndex(int index){
        return this.selectedIndex;
    }

    public T getSelection(){
        return this.elements.get(this.selectedIndex);
    }

}
