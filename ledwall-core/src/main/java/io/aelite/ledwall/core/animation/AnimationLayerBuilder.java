package io.aelite.ledwall.core.animation;

public class AnimationLayerBuilder {

    private Class<? extends AnimationLayer> type;

    public AnimationLayerBuilder(Class<? extends AnimationLayer> type){
        this.type = type;
    }

    public Class<? extends AnimationLayer> getType(){
        return this.type;
    }

    public AnimationLayer newAnimationLayer(){
        try {
            return this.type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate new animation layer", e);
        }
    }

}
