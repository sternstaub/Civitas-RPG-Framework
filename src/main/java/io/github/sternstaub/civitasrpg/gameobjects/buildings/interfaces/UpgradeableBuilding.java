package io.github.sternstaub.civitasrpg.gameobjects.buildings.interfaces;

import io.github.sternstaub.civitasrpg.handlers.config.flags.BuildingConfigFlag;

public interface UpgradeableBuilding {
    public static BuildingConfigFlag[] requiredFlags = {};
    int getMaxLevel();
    void setLevel(int level);

}
