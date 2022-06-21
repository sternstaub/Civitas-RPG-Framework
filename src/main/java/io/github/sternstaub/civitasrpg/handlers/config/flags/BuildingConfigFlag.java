package io.github.sternstaub.civitasrpg.handlers.config.flags;

import io.github.sternstaub.civitasrpg.backend.exceptions.ConfigEntryClassMismatchException;
import io.github.sternstaub.civitasrpg.backend.exceptions.RangedConditionOutOfBoundException;
import io.github.sternstaub.civitasrpg.gameobjects.buildings.BuildingType;
import io.github.sternstaub.civitasrpg.handlers.config.conditions.ConfigEntryConditionRanged;
import io.github.sternstaub.civitasrpg.handlers.config.conditions.ConfigEntryCondition;

import javax.annotation.Nullable;
import java.util.ArrayList;

import static io.github.sternstaub.civitasrpg.CivitasRPG.PLUGIN;

/**
 * any building configuration file needs to hold all of these keys
 * the reason for this is that each building object will have exactly one setting for each of these entries
 */
public enum BuildingConfigFlag implements ConfigFlag {
    BUILDING_TYPE(
            "building.type",
            // new String[]{"anchor", "default"}
            BuildingType.values()
    ),
    BUILDING_UPGRADABLE(
            "building.upgradable",
            Boolean.class),
    UPGRADABLE_TYPE(
            "upgradable.type",
            new String[]{"anchor"}),
    UPGRADABLE_MAXLEVEL(
            "upgradable.maxlevel",
            Integer.class
    )

    ;


    private final String key;
    private ConfigEntryCondition condition;
    // public final Class type;
    private ArrayList<BuildingConfigFlag> requiredsubEntries; // some entries require further specification. for example, the "upgradable" flag also needs max level.

// ========================
    // ======================== CONSTRUCTOR SECTION
    BuildingConfigFlag(String ymlPath, Object[] conditions, ArrayList<BuildingConfigFlag> requiredsubEntries) {
        this.key = ymlPath;
        this.requiredsubEntries = requiredsubEntries;
        if(conditions == null) {
            PLUGIN.log("CITICAL: invalid definition for BuildingConfigFlag - no condition value specified!");
        } else {
            try {
                this.condition = new ConfigEntryConditionRanged(conditions);
            } catch (ConfigEntryClassMismatchException | RangedConditionOutOfBoundException e) {
                throw new RuntimeException(e); }
        }
    }

    BuildingConfigFlag(String ymlPath, Object[] conditions) {
        this.key = ymlPath;
        this.requiredsubEntries = null;
        if(conditions == null) {
            PLUGIN.log("CITICAL: invalid definition for BuildingConfigFlag - no condition value specified!");
        } else {
            try {
                this.condition = new ConfigEntryConditionRanged(conditions);
            } catch (ConfigEntryClassMismatchException | RangedConditionOutOfBoundException e) {
                throw new RuntimeException(e); }
        }
    }

    /**
     * this constructor is only for the Building Type flag.
     * it means that the value of the config entry "building.flag" must equal one of the constants from BuildingType enum
     * @param ymlPath path to config.yml
     * @param allowedTypes
     */
    BuildingConfigFlag(String ymlPath, BuildingType[] allowedTypes) {
        this.key = ymlPath;
        this.requiredsubEntries = null;
        //For this constructor, a new ValueCondition must be created from enum names.
        //It will allow all the names from the BuildingType enum as values, thus parse these names into a stringArray and create condition from it
        String[] conditionStringValues = new String[allowedTypes.length];
        int i = 0;
        for(BuildingType type : allowedTypes) {
            conditionStringValues[i] = type.toString();
            i++;
        }
        //Now put the temp array into a new Condition and pass it to this Enum constant
        try {
            this.condition = new ConfigEntryConditionRanged(conditionStringValues);
            } catch (ConfigEntryClassMismatchException | RangedConditionOutOfBoundException e) {
                throw new RuntimeException(e); }

    }



    // =============================== CONSTRUCTOR END
// ============================
    BuildingConfigFlag(String ymlPath, Class<?> condition) {
        this.key = ymlPath;
        this.condition = new ConfigEntryCondition(condition);
    }

    @Override
    public ConfigEntryCondition getCondition() {
        return condition;
    }

    @Nullable
    public ArrayList<BuildingConfigFlag> getRequiredsubEntries() {
        return requiredsubEntries;
        //TODO : adding subsections for reading multiple values to 1 config flag
    }
    @Override
    public String getYamlKey() {
        return key;
    }

    @Override
    public <T extends ConfigFlag> T getInstance() {
        return (T) this;
    }

    @Override
    public Object getDefaultValue() {
        return null;
    }

    @Override
    public boolean usesDefaults() {
        return false;
    }
    @Override
    public String getName() {
        return this.name();
    }
}
