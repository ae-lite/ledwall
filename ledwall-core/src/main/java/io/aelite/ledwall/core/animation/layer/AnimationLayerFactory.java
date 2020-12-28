package io.aelite.ledwall.core.animation.layer;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AnimationLayerFactory {

    private static Logger logger = LoggerFactory.getLogger(AnimationLayerFactory.class);

    private Map<String, AnimationLayerInstantiator> instantiators;

    public AnimationLayerFactory(){
        this.instantiators = new HashMap<String, AnimationLayerInstantiator>();
    }

    public void loadInstantiators(Reflections reflections){
        Set<Class<? extends AnimationLayer>> classes = reflections.getSubTypesOf(AnimationLayer.class);
        int count = 0;

        for(Class<? extends AnimationLayer> aClass : classes){
            if(aClass.isAnnotationPresent(LedWallAnimationLayer.class)){
                LedWallAnimationLayer animationLayer = aClass.getAnnotation(LedWallAnimationLayer.class);
                String name = animationLayer.name();
                String description = animationLayer.description();
                AnimationLayerInstantiator instantiator = new AnimationLayerInstantiator(name, description, aClass);
                this.instantiators.put(instantiator.getType(), instantiator);
                count++;
            }
        }

        logger.info("Loaded " + count + " AnimationLayer(s).");
    }

    public List<AnimationLayerInstantiator> getInstantiators(){
        return new ArrayList<AnimationLayerInstantiator>(instantiators.values());
    }

    public AnimationLayer newAnimationLayer(String type) throws InstantiationException, IllegalAccessException {
        return this.instantiators.get(type).newLayer();
    }

}
