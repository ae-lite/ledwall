package io.aelite.ledwall.core.blendmode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BlendModeFactory {

    private Map<String, BlendMode> blendModes;

    public BlendModeFactory(){
        this.blendModes = new HashMap<String, BlendMode>();

        this.blendModes.put("Normal", new NormalBlendMode());
    }

    public BlendMode getBlendMode(String name){
        return this.blendModes.get(name);
    }

    public Set<String> getBlendModes(){
        return this.blendModes.keySet();
    }

}
