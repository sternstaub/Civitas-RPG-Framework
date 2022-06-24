package io.github.sternstaub.civitasrpg;

import io.github.sternstaub.civitasrpg.config.ConfigEntry;
import io.github.sternstaub.civitasrpg.config.ConfigFile;
import io.github.sternstaub.civitasrpg.config.ConfigPack;
import io.github.sternstaub.civitasrpg.exceptions.config.ConfigEntryConditionFailedException;
import io.github.sternstaub.civitasrpg.flags.config.ConfigFlagBuilding;
import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlag;
import io.github.sternstaub.civitasrpg.flags.config.ConfigFolderFlags;
import io.github.sternstaub.civitasrpg.flags.config.ConfigFlagsMain;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.lang.constant.Constable;
import java.util.*;

public class CivitasConfigHandler {

    private static final CivitasRPG plugin = CivitasRPG.PLUGIN;
    public final ConfigFile<ConfigFlagsMain> mainConfig;
    public final ConfigPack buildingConfigPack;

// ========================================================================
// =================================================
// ====================== Initialization
// ==================================
// ======================

// This will also check if all the required configuration keys are represented in the yml files.
// Missing entries will be set to a default value.
public
CivitasConfigHandler() {
        File mainFile = new File(plugin.configPath + "main.yml");
        mainConfig = new ConfigFile<>(
                "main", ConfigFolderFlags.MAIN, getConfigEntriesForFile(mainFile, ConfigFlagsMain.values()));
        //IngameBuilding config initialization
        buildingConfigPack = new ConfigPack(ConfigFolderFlags.BUILDING);

        // ...get sub configs from each yaml file in the root folder of the building config,
        // load them into the configPack
        for(Map.Entry<String, ArrayList<ConfigEntry<?>>> set : this.getConfigEntriesForRoot(ConfigFolderFlags.BUILDING, ConfigFlagBuilding.values()).entrySet()) {
            var tempSubConfig = new ConfigFile<ConfigFlagBuilding>(set.getKey(), ConfigFolderFlags.BUILDING, set.getValue());
            if(tempSubConfig.isValid())
                buildingConfigPack .addSubConfig(tempSubConfig);
        }

        //debug loop
        for(ConfigFile<?> bconf : buildingConfigPack.getSubConfigs()) {
            plugin.debug(this, "Debugging " + bconf.getName());

            bconf.getFlagsAndValues().forEach((key, entry) ->
                    plugin.debug(this, "IngameBuilding Config " + bconf.getName() + " - entry found: '"+ entry.toString() + "'"));
        }
    }
// ===================
// ================================
// ======================= ^ INITIALIZATION END ^
// ==============================================
// ========================================================================

// ========================================================================
// =================================================
// ====================== v File reading algorithms v
// ==================================
// ======================

/**
 * this will get all valid config entries for each file in the given root config folder.
 * it calls another method (algorithm) to do so.
 * @param root Enum Constant for the root context of this config pack. it contains the folder path.
 * @param possibleConfigFlag the allowed types of config flag for this
 * @return a map with the file name and an array of the contained values
 * @param <Flag>
 */
    @Nullable
    private <Flag extends AbstractConfigFlag> HashMap<String, ArrayList<ConfigEntry<?>>>
getConfigEntriesForRoot
(
    ConfigFolderFlags root,
    Flag[] possibleConfigFlag
){
        plugin.debug                                                                           (this,"Reading sub configurations from root folder " +plugin.configPath +root.folderPath);
    // initialize reference for reading directory
        File dir = new File(plugin.configPath + root.folderPath);
    //initialize output variable
        var output = new HashMap<String, ArrayList<ConfigEntry<?>>>();
    // if there is no dir or no proper input, nothing to do
        if(!dir.exists()) {
            plugin.log                                                                        ("Subconfig directory "+dir.getAbsolutePath()+ " does not exist. Creating it, but reading no configurations...");
            dir.mkdirs();
            return null;} // return empty list.
        if(possibleConfigFlag.length == 0)
            return null;
    //else (if the config pack sub folder exists)...
        // for each file in the given folder
        for(File f : Objects.requireNonNull(dir.listFiles())) {
                    plugin.debug                                                                        (this, "Loading " + f.getAbsolutePath());
                    output.put(
                        f.getName().substring(0, f.getName().lastIndexOf(".")), //cutting the file extension
                        getConfigEntriesForFile(f, possibleConfigFlag)); // algorithm that parses contents of a yml file into a list of ConfigEntry objects

        //END foreach
    // repeat for all yaml files in the folder, then return output.
        }
    // all files have been checked
        plugin.debug                                                                                    (this,"Initializing Config pack...", true);
        return output;
    }

@NotNull
private <ConfFlag extends AbstractConfigFlag> ArrayList<ConfigEntry<?>>
getConfigEntriesForFile
(   File f,
    ConfFlag[] possibleConfigFlag
){
        var configContent = new ArrayList<ConfigEntry<?>>();
        if (!f.getName().endsWith("yml"))
            return configContent;
    // read yamlConfiguration from file, init output value
        var yaml = YamlConfiguration.loadConfiguration(f);
        plugin.debug(this, "Getting config entries for file "+f.getAbsolutePath());
        plugin.debug(this, "Found keys: " +yaml.getKeys(true));
    // for each applicable config flag, look in the yaml for respective value.
        for(AbstractConfigFlag flag : possibleConfigFlag) {
            plugin.debug(this, "now checking " +f.getAbsolutePath() + " for the flag " + flag.getYamlKey());
            try {
                if(!yaml.contains(flag.getYamlKey())) {
                    plugin.debug(this, "No entry found for " + flag.getYamlKey());
                    if(flag.usesDefaults()) //the "default" setting is stored in the respective AbstractConfigFlag Enum.
                        configContent.add(new ConfigEntry(flag, flag.getDefaultValue()));
                    continue;
                    }

                if(flag.getCondition().isFulfilledFor(Objects.requireNonNull(yaml.get(flag.getYamlKey())))) {
                    //success; load from yml file...
                    plugin.debug                        (this, "The file "+f.getName()+" contains an entry for "+flag.getYamlKey()+", which fulfills the condition "+flag.getCondition()+" reading it...");
                    configContent.add(new ConfigEntry<>(flag, yaml.get(flag.getYamlKey())));
                    continue;
                }
                plugin.debug               (this, "The config "+f.getName()+" is not set to use default values.");
                plugin.debug                (this, "No valid entry under the key "+flag.getYamlKey() + "; no value will be set.");
            } catch (ConfigEntryConditionFailedException e) {e.printStackTrace();}
        } // next iteration ^
    // END foreach
    return configContent;
    }

// =========================================================================
// =========================================================
// ====================== Public Section
// ===============================
// ====================

public void
saveAll
() {
        try {
            mainConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}