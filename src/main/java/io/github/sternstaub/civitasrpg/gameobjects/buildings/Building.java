package io.github.sternstaub.civitasrpg.gameobjects.buildings;

import io.github.sternstaub.civitasrpg.handlers.config.ConfigEntry;

import java.util.ArrayList;

public abstract class Building {

    ArrayList<ConfigEntry> givenConfiguration = new ArrayList<>();

    /**
     * Represents a building to be set up with certain config values
     * @param configuration the values, like the range etc.
     */
    public Building(ArrayList<ConfigEntry> configuration) {

        givenConfiguration = configuration; // adding config values to this building (like type, range etc.)




    }

    public abstract boolean areConfigDependenciesMet();



}
