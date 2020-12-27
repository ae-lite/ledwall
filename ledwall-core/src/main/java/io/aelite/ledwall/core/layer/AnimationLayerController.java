package io.aelite.ledwall.core.layer;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnimationLayerController {

    private List<AnimationLayerFactory> animationLayerFactories;

    public AnimationLayerController(){
        this.animationLayerFactories = new ArrayList<AnimationLayerFactory>();
    }

    public void loadAnimationLayerFactories(Reflections reflections){
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(AnimationLayer.class);
        for(Class aClass : classes){
            AnimationLayer animationLayer = (AnimationLayer) aClass.getAnnotation(AnimationLayer.class);
            String name = animationLayer.name();
            String description = animationLayer.description();
            this.animationLayerFactories.add(new AnimationLayerFactory(name, description, aClass));
        }
    }

    public List<AnimationLayerFactory> getAnimationLayerFactories() {
        return animationLayerFactories;
    }

}
