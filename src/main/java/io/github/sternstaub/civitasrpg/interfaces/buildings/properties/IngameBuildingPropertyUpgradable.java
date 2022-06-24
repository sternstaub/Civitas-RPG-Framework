package io.github.sternstaub.civitasrpg.interfaces.buildings.properties;

import io.github.sternstaub.civitasrpg.flags.config.ConfigFlagBuilding;

public interface IngameBuildingPropertyUpgradable extends IngameBuildingProperty {
    public static ConfigFlagBuilding[] requiredFlags = {};
    int getMaxLevel();
    void setLevel(int level);

}
