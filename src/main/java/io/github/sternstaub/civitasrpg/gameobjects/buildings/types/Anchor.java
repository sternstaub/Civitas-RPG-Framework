package io.github.sternstaub.civitasrpg.gameobjects.buildings.types;

import io.github.sternstaub.civitasrpg.gameobjects.buildings.Building;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigEntry;

import java.util.ArrayList;

public class Anchor extends Building {

    /**
     * Represents a building to be set up with certain config values
     *
     * @param configuration the values, like the range etc.
     */
    public Anchor(ArrayList<ConfigEntry> configuration) {
        super(configuration);
    }

    @Override
    public boolean areConfigDependenciesMet() {
        return false;
    }
}
