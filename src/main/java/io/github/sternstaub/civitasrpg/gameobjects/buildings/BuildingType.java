package io.github.sternstaub.civitasrpg.gameobjects.buildings;

import io.github.sternstaub.civitasrpg.handlers.config.flags.BuildingConfigFlag;

import java.util.ArrayList;

public enum BuildingType {

    ANCHOR

    ;
    private final ArrayList<BuildingConfigFlag> requiredFlags = new ArrayList<>();

    private BuildingType() {

    }
}
