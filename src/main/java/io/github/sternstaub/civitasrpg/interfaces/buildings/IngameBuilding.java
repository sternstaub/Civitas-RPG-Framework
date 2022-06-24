package io.github.sternstaub.civitasrpg.interfaces.buildings;

import io.github.sternstaub.civitasrpg.interfaces.CivitasGameObjectFlagable;
import io.github.sternstaub.civitasrpg.flags.interfaces.building.AbstractBuildingFlag;

import java.util.ArrayList;

/**
 * an ingame building that usees flagged configuration properties, like Upgradable
 */
public interface IngameBuilding extends CivitasGameObjectFlagable {

    abstract ArrayList<AbstractBuildingFlag> getBuildingFlags();

}
