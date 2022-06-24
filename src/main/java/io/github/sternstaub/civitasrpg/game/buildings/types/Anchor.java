package io.github.sternstaub.civitasrpg.game.buildings.types;

import io.github.sternstaub.civitasrpg.flags.building.BuildingTypeFlag;
import io.github.sternstaub.civitasrpg.game.buildings.Building;
import io.github.sternstaub.civitasrpg.config.ConfigEntry;
import io.github.sternstaub.civitasrpg.interfaces.CivitasFlag;
import io.github.sternstaub.civitasrpg.flags.interfaces.building.AbstractBuildingFlag;

import java.util.ArrayList;

public class Anchor extends Building {

    /**
     * Represents a building to be set up with certain config values
     *
     * @param configuration the values, like the range etc.
     */
    public Anchor(ArrayList<ConfigEntry> configuration) {
        super(BuildingTypeFlag.ANCHOR); // this building holds the flag fro the "anchor" type
    }

    @Override
    public ArrayList<CivitasFlag> getCivitasFlags() {
        return null;
    }

    @Override
    public ArrayList<AbstractBuildingFlag> getBuildingFlags() {
        return null;
    }
}
