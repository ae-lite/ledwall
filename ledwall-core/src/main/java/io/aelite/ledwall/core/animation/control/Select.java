package io.aelite.ledwall.core.animation.control;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Select<K, V> extends Control {

    private Map<K, V> elements;
    private K selection;

    public Select() {
        this.elements = new LinkedHashMap<K, V>();
    }

    public void put(K key, V value){
        if(this.selection == null){
            this.selection = key;
        }
        this.elements.put(key, value);
    }

    public List<K> getElements() {
        return new ArrayList<K>(this.elements.keySet());
    }

    public void select(K key){
        this.selection = key;
    }

    public K getSelection(){
        return this.selection;
    }

    @JsonIgnore
    public V getSelectedValue(){
        return this.elements.get(this.selection);
    }

}
