package io.aelite.ledwall.layerpackage;

import io.aelite.ledwall.core.layer.AnimationLayer;
import io.aelite.ledwall.core.layer.Color;
import io.aelite.ledwall.core.layer.control.Control;

@AnimationLayer(name = "Static Color", description = "Lights up the entire LedWall in one color.")
public class StaticColor {

    @Control("Color")
    private Color color;

    //TODO: implement

}
