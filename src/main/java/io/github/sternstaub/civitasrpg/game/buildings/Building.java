package io.github.sternstaub.civitasrpg.game.buildings;

import io.github.sternstaub.civitasrpg.flags.interfaces.building.AbstractBuildingFlag;
import io.github.sternstaub.civitasrpg.flags.building.BuildingTypeFlag;
import io.github.sternstaub.civitasrpg.interfaces.buildings.IngameBuilding;
import org.bukkit.Chunk;

import java.util.ArrayList;

public abstract class Building implements IngameBuilding {

    protected BuildingTypeFlag type;
    ArrayList<AbstractBuildingFlag> givenFlags = new ArrayList<>();

    /**
     * Represents a building to be set up with certain config values
     * @param type the values, like the range etc.
     */
    public Building(BuildingTypeFlag type) {
        this.type = type;
    }

    public BuildingTypeFlag getType() {
        return type;
    }
}
