package io.github.sternstaub.civitasrpg.handlers.config.building;

import io.github.sternstaub.civitasrpg.exceptions.ConditionParametersClassMismatchException;
import io.github.sternstaub.civitasrpg.flags.BuildingConfigFlag;
import io.github.sternstaub.civitasrpg.flags.RootConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.config.*;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;

public class BuildingConfigPack extends ConfigPack {

    private final ArrayList<BuildingConfig> registeredBuildingConfigs = new ArrayList<>();

    // =================================================
    // ====================== Initialization
    // ==================================

    public BuildingConfigPack() {
        super(RootConfigFlag.BUILDINGS);
                                                                                            plugin.debug(this,"Initializing Class...");

        //do the work for subconfig files:                                            fetch yaml configurations from the subfolder which belongs to this config.
        for(YamlConfiguration yaml : this.getYamlConfigsFromFolder()) {              // if there are any files specified, loop through all of them and save them into this class and for each subconfig, check for occurrance of any key contained in BuildingConfigFlag
            EnumMap<BuildingConfigFlag, ConfigValue>
            tempValidatedEntriesWithValues = new EnumMap<BuildingConfigFlag, ConfigValue>(BuildingConfigFlag.class); // for each config file

            //loop through the enabled configuration flags for each yaml and map all valid entries from yaml into the temp variable
                    for(BuildingConfigFlag key : BuildingConfigFlag.values()) {
                        if(!yaml.contains(key.path))
                            continue;
                        plugin.debug(this, "The file "+yaml.getString("name")+" contains an entry for "+key.path+", reading it...");
                            try {
                                if(key.condition.isFulfilledForValueObject(yaml.get(key.path))) // ensures that no exception is thrown
                                // yml file contains a config key with a value that fulfills the condition for that confKey
                                    tempValidatedEntriesWithValues.put(key, new ConfigValue(yaml.get(key.path), key.condition));  } catch (ConditionParametersClassMismatchException e) {throw new RuntimeException(e);}

                    }
            BuildingConfig output = new BuildingConfig(yaml.getString("name"), tempValidatedEntriesWithValues);

            if(output.checkIntegrityAndLoad()) {            //when integrity check of the file has succeeded
                subConfigs.add(output);                                                 //add the building from given yaml file and store it in a property of this metaconfig-class
                plugin.debug                                                                    (this,"Registered compatible sub config with name "+yaml.getString("name"));

                registeredBuildingConfigs.add(new BuildingConfig(yaml.getString("name"), tempValidatedEntriesWithValues));
            }}



        if(super.getYamlConfigsFromFolder().isEmpty())                                  // getYamlConfigsFromFolder() method is inherited from ConfigPack: to get all ymlFiles in pack folder
            return;

        //loop finish; work done; success!
        plugin.debug(this,"Configuration integrity check finished successful!");
        return;
    }

    public void saveAll() throws IOException {
        plugin.debug                                (this, "Saving configurations for " + this.getClass().getSimpleName());
        for(ConfigFile ccf : subConfigs) {
            ccf.save();
        }
    }

    public ArrayList<BuildingConfig> getRegisteredBuildingConfigs() {
        return registeredBuildingConfigs;
    }
}
