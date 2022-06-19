package io.github.sternstaub.civitasrpg.handlers.config.building;

import io.github.sternstaub.civitasrpg.flags.BuildingConfigFlag;
import io.github.sternstaub.civitasrpg.flags.RootConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigFile;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigValue;

import javax.annotation.Nullable;
import java.util.EnumMap;

public class BuildingConfig extends ConfigFile {

    // storage for all building config flags that were applied from its config
    private EnumMap<BuildingConfigFlag, ConfigValue> configKeyValues;
    private String name;

    protected BuildingConfig(String filename, EnumMap<BuildingConfigFlag, ConfigValue> flags) {
        super(RootConfigFlag.BUILDINGS.folderPath, filename, RootConfigFlag.BUILDINGS);
        // initialize the main pack config (this class) by giving a file
        configKeyValues = flags;
        this.name = filename;
    }

    @Override
    public boolean checkIntegrityAndLoad(){
        if(configKeyValues.isEmpty())
            return false;
        return true;
    }
    @Nullable
    public ConfigValue getConfigValue(BuildingConfigFlag f) {
        if(!configKeyValues.containsKey(f))
            return null;
        return configKeyValues.get(f);
    }

    public EnumMap<BuildingConfigFlag, ConfigValue> getConfigKeyValues() {
        return configKeyValues;
    }
    @Override
    public String toString() {
        return name;
    }
}
