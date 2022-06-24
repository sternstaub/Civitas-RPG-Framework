package io.github.sternstaub.civitasrpg.flags.building;

import io.github.sternstaub.civitasrpg.flags.config.ConfigFlagBuilding;
import io.github.sternstaub.civitasrpg.flags.interfaces.building.AbstractBuildingTypeFlag;

public enum BuildingTypeFlag implements AbstractBuildingTypeFlag {

    ANCHOR("anchor");

    ;


    // The key to whom this enum serves as value set
    private final ConfigFlagBuilding assignedConfigKey = ConfigFlagBuilding.BUILDING_TYPE;
    private final String flagName;

// Constructor
    private BuildingTypeFlag(String flagName) {
        this.flagName = flagName;
    }
// \constructor
    @Override
    public ConfigFlagBuilding getConfigFlag() {
        return assignedConfigKey;
    }
    @Override
    public String getName() {
        return flagName;
    }
}
