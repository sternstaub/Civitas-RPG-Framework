package io.github.sternstaub.civitasrpg.flags.building;

import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlag;
import io.github.sternstaub.civitasrpg.flags.interfaces.building.AbstractBuildingPropertyFlag;

public enum BuildingPropertyFlag implements AbstractBuildingPropertyFlag {

    UPGRADABLE("Upgradable");

    private final String flagName;

    BuildingPropertyFlag(String flagName) {
        this.flagName = flagName;
    }

    @Override
    public AbstractConfigFlag getConfigFlag() {
        return null;
    }

    @Override
    public String getName() {
        return flagName;
    }
}
