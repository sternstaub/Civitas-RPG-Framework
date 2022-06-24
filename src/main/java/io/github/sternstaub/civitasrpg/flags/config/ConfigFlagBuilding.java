package io.github.sternstaub.civitasrpg.flags.config;

import io.github.sternstaub.civitasrpg.config.ConfigCondition;
import io.github.sternstaub.civitasrpg.flags.interfaces.building.AbstractBuildingFlag;
import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlag;
import io.github.sternstaub.civitasrpg.flags.building.BuildingTypeFlag;
import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlagBuilding;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * any building configuration file needs to hold all of these keys
 * the reason for this is that each building object will have exactly one setting for each of these entries
 */
public enum ConfigFlagBuilding implements AbstractConfigFlagBuilding {
    BUILDING_TYPE(
            "building.type",
            "BuildingType",
            null, // does not rely on any sub entries.
            BuildingTypeFlag.values()) // can assume all the getNames() of the buildingType Enum constants as values
    , BUILDING_RADIUS(
            "building.radius", "BuildingRadius",
            null,
            new ConfigCondition<Integer>(5, 30) // really just an entry for testing ranged numeric conditions
    )
    ;
    private final String flagName;
    private final String key;
    private ConfigCondition<?> condition;
    // the conditions which have to be fulfilled for a config entry to be considered valid.
    private final ArrayList<ConfigFlagBuilding> requiredsubEntries = new ArrayList<>();
    // some entries require further specification. for example, the "upgradable" flag also needs max level.
    private final ArrayList<AbstractBuildingFlag> targetFlag = new ArrayList<>(); //the enum constants
    // the config


// ========================
    // ======================== CONSTRUCTOR SECTION
    /**
     * default constructor
     *
     * @param ymlPath the key of the config flag as is used by yaml parsers
     * @param flagName lowercase name of the given enum constant
     * @param reqSubEntries A whitelist of values allowed for this config flag / key
     */
ConfigFlagBuilding(
        String ymlPath,
        String flagName,
        @Nullable ConfigFlagBuilding[] reqSubEntries,
        ConfigCondition<?> condition)
    {
        this.flagName = flagName;
        this.key = ymlPath;
        this.condition = condition;
        if(reqSubEntries != null)
            this.requiredsubEntries.addAll(Arrays.asList(reqSubEntries));
    }
    /**
     * Constructor for config entries that should be parsable into ingame flags
     * @param assignableIngameFlags A list of BuildingFlags which can be values for this config flag/key
     */
    ConfigFlagBuilding(
    String ymlPath,
    String flagName,
    @Nullable ConfigFlagBuilding[] reqSubEntries,
    @NotNull AbstractBuildingFlag[] assignableIngameFlags)
    {
        // Call the other constructor, but the permission will be created after the this() call.
        this(ymlPath, flagName, reqSubEntries, (ConfigCondition<?>) null);
        // Generate a condition which accepts String values as entries.
        ConfigCondition<String> tempCondition = new ConfigCondition<String>(String.class);
        for(AbstractBuildingFlag flag : assignableIngameFlags) {
            tempCondition.addAcceptedValue(flag.getName());
        }
        this.condition = tempCondition; // the condition was set to null; now put something into it!
}

    // =============================== CONSTRUCTOR END
// ============================






// ============================
    // =============================== GETTER SECTION

    @Override
    public ConfigCondition getCondition() {
        return condition;
    }

    @Override
    public String getYamlKey() {
        return key;
    }

    @Override
    public <T extends AbstractConfigFlag> T getInstance() {
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

    @org.jetbrains.annotations.Nullable
    @Override
    public AbstractBuildingFlag getParsable() {
        return null;
    }
}
