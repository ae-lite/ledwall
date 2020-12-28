package io.aelite.ledwall.core.animation.layer;

public class AnimationLayerInstantiator {

    private String name;
    private String description;
    private Class<? extends AnimationLayer> animationLayerClass;

    public AnimationLayerInstantiator(String name, String description, Class<? extends AnimationLayer> animationLayerClass){
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

    public AnimationLayer newLayer() throws IllegalAccessException, InstantiationException {
        return this.animationLayerClass.newInstance();
    }

    public String getType(){
        return this.animationLayerClass.getName();
    }

}
