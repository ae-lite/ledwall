package io.aelite.ledwall.core.layer;

public class AnimationLayerFactory {

    private String name;
    private String description;
    private Class<?> animationLayerClass;

    public AnimationLayerFactory(String name, String description, Class<?> animationLayerClass){
        this.name = name;
        this.description = description;
        this.animationLayerClass = animationLayerClass;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Object newLayer() throws IllegalAccessException, InstantiationException {
        return this.animationLayerClass.newInstance();
    }

}
