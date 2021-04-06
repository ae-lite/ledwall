package io.aelite.ledwall.restplugin.handler;

import io.aelite.ledwall.core.LedWallApplication;
import io.aelite.ledwall.core.Animation;
import io.aelite.ledwall.restplugin.dto.AnimationDto;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class GetAnimationsHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        List<Animation> animations = LedWallApplication.INSTANCE.getAnimationController().getAnimations();
        List<AnimationDto> animationDtos = animations.stream().map(AnimationDto::new).collect(Collectors.toList());
        context.json(animationDtos);
    }

}
