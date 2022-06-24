package io.github.sternstaub.civitasrpg.flags.interfaces.config;

import io.github.sternstaub.civitasrpg.flags.config.ConfigFlagBuilding;
import io.github.sternstaub.civitasrpg.flags.interfaces.building.AbstractBuildingFlag;

import javax.annotation.Nullable;

public interface AbstractConfigFlagBuilding extends AbstractConfigFlag {

    @Nullable
    abstract AbstractBuildingFlag getParsable();
}
