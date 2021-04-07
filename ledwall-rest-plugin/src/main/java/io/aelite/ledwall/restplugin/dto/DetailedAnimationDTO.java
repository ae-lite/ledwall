package io.aelite.ledwall.restplugin.dto;

import io.aelite.ledwall.core.animation.Animation;

import java.util.List;
import java.util.stream.Collectors;

public class DetailedAnimationDTO extends AnimationDTO {

    private List<AnimationLayerDTO> animationLayers;

    public DetailedAnimationDTO(Animation animation) {
        super(animation);
        this.animationLayers = animation.getAnimationLayers().stream().map(AnimationLayerDTO::new).collect(Collectors.toList());
    }

    public List<AnimationLayerDTO> getAnimationLayers() {
        return animationLayers;
    }

}
