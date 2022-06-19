package io.github.sternstaub.civitasrpg.handlers.config.civilization;

import io.github.sternstaub.civitasrpg.exceptions.ConditionParametersClassMismatchException;
import io.github.sternstaub.civitasrpg.exceptions.NumericConfigConditionOutOfBoundException;
import io.github.sternstaub.civitasrpg.handlers.config.CivitasConfigFile;
import io.github.sternstaub.civitasrpg.handlers.config.CivitasConfigPack;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigRoot;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigValueCondition;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class BuildingConfigMeta extends CivitasConfigPack {


    private final ArrayList<CivitasConfigFile> subConfigurations =  new ArrayList<CivitasConfigFile>();
    // storage for all valid buildingConfigs, will be filled by checkIntegrityAndLoad
    private final EnumMap<BuildingConfigKey, ConfigValueCondition> configValueConditions = new EnumMap<BuildingConfigKey, ConfigValueCondition>(BuildingConfigKey.class);




    // =================================================
    // ====================== Initialization
    // ==================================


    public BuildingConfigMeta() {
        super(ConfigRoot.BUILDINGS);
// DEBUG ->>>
                                                                                                plugin.debug(this,
                                                                                                        "Initializing Class...");
// DEBUG ->>>
        try {
            configValueConditions.put(BuildingConfigKey.BUILDING_TYPE,
                    new ConfigValueCondition("".getClass(), new String[]{"anchor", "default"}));
            configValueConditions.put(BuildingConfigKey.TEST,
                    new ConfigValueCondition("".getClass(), null));
        }
// CATCH ->>>
                                                                                                catch (ConditionParametersClassMismatchException e) {
                                                                                                    throw new RuntimeException(e);
                                                                                                } catch (NumericConfigConditionOutOfBoundException e) {
                                                                                                    throw new RuntimeException(e);
// CATCH ->>>
        }
    }

    // =================================================
    // ====================== Getters
    // ============
    @Override
    protected List<CivitasConfigFile> getPackSubConfigFiles() {
        return subConfigurations;
    }




    // ===============================================================================================================================
    //==============================================================================================================
    // ======================               Public methods:                 ================================
    // ====================== checkIntegrityAndLoad(), isCompatibleWithYaml() ===========================
    // ===============================================================================================
    // ==============================================================================
    /**
     * check integrity of the files within the subconfig folder
     * and load all valid files into subConfiguration HashMap
     */
    @Override
    public boolean checkIntegrityAndLoad() {

        ArrayList<YamlConfiguration> ymlConfList
                    = super.getYamlConfigsFromFolder();                                 // getYamlConfigsFromFolder() method is inherited from ConfigPack: to get all ymlFiles in pack folder

        if(ymlConfList.isEmpty())
            return false;
        for(YamlConfiguration yaml : ymlConfList) {                                     // if there are any files specified, loop through all of them and save them into this class
            if (!this.isCompatibleWithYaml(yaml)) {                                     // true when given yml file can't be applied to construct BuildingConfig objects, skip it.
// DEBUG ->>>
                                                                                        plugin.debug(this,  "Building Configuration for "+yaml.getString("name")+" does not seem to be a valid building configuration, skipping...");
                continue; }
            BuildingConfig output = new BuildingConfig(yamlConfig.getString("name"));
            if(output.checkIntegrityAndLoad()) //when integrity check of the file has succeeded
            {
                this.subConfigurations.add(output);                                     //add the building from given yaml file and store it in a property of this metaconfig-class
// DEBUG ->>>
                                                                                        plugin.debug(this, "Registered compatible sub config with name "+yamlConfig.getString("name"));
        }}
// loop finish; success!
                                                                                        plugin.debug(this, "Configuration integrity check finished successful!");
        return true;
    }

/**
 * Check if a given YamlConfiguration is compatiple with the building config pack.
 * it will be checked if the yaml contains all keys required by this type of configuration (buildingConfig),
 * and if all the values fulfill the conditions
 * @param yconf the yml configuration to check for readability
 * @return true if the given yml file contains all keys required for a working buildingConfig
 */
    @Override
    protected boolean isCompatibleWithYaml(YamlConfiguration yconf) {

        // Compare enumerated possible keys for building configuration and the requirements of their respective
        // values with content of given buildings/buildingX.yml file from the fct parameter.
        for(BuildingConfigKey bck : BuildingConfigKey.values()) {
            ConfigValueCondition
                    condition = configValueConditions.get(bck);

                                                                                        plugin.debug(this,"Check '"+bck.path+"' entry in '"+yconf.get("name")+
                                                                                                ".yml' for conditions of "+ bck.toString());
            if(!yconf.contains(bck.path)) {
                plugin.log(yconf.get("name") + ".yml could not be validated - " +
                        "missing config key: " + bck.path);
                return false;
            }
            if(!condition.isFulfilledForValueObject(yconf.get(bck.path))) {
                plugin.log(yconf.get("name") + ".yml could not be validated - value condition not fulfilled: "+condition.toString());
                plugin.debug(this, "Given value for failed condition check was "+ yconf.getString(bck.path));
            }
        }
        return true;
    }

}
