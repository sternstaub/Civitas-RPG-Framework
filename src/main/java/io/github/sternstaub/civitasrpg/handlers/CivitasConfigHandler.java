package io.github.sternstaub.civitasrpg.handlers;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.backend.exceptions.ConfigEntryClassMismatchException;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigEntry;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigFile;
import io.github.sternstaub.civitasrpg.handlers.config.flags.ConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.config.flags.ConfigRoots;
import io.github.sternstaub.civitasrpg.handlers.config.flags.BuildingConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigPack;
import io.github.sternstaub.civitasrpg.handlers.config.flags.MainConfigFlag;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CivitasConfigHandler {

    private static final CivitasRPG plugin = CivitasRPG.PLUGIN;
    public static final String META_FILE_NAME = "_meta.yml";
    public final ConfigFile<MainConfigFlag> mainConfig;
    public final ConfigPack buildingConfigPack;

// ========================================================================
// =================================================
// ====================== Initialization
// ==================================
// ======================

// CivitasConfigurationFiles have a method to load the .yml files from disk and parse their content into java objects.
// it is called initializeValues().
// This will also check if all the required configuration keys are represented in the yml files.
// Missing entries will be set to a default value.
    public CivitasConfigHandler() {
        File mainFile = new File(plugin.configPath + "main.yml");
        mainConfig = new ConfigFile<MainConfigFlag>(
                "main", ConfigRoots.MAIN, getConfigEntriesForFile(mainFile, MainConfigFlag.values()));

    //Building config initialization
        ArrayList<ConfigFile>                   buildingConfigs = new ArrayList<>();
        // ...get sub configs for each, store into this ^ variable, then create new config pack
        for(Map.Entry<String, ArrayList<ConfigEntry>> set: this.getConfigEntriesForRoot(ConfigRoots.BUILDINGS, BuildingConfigFlag.values()).entrySet()) {
            buildingConfigs.add(new ConfigFile(set.getKey(), ConfigRoots.BUILDINGS, set.getValue()));
        }
        buildingConfigPack = new ConfigPack(ConfigRoots.BUILDINGS, buildingConfigs);

        //debug loop
        for(ConfigFile bconf : buildingConfigPack.getSubConfigs()) {
            plugin.debug(this, "Debugging " + bconf.getName());

            bconf.getFlagsAndValues().forEach((key, entry) ->
                    plugin.debug(this, "Building Config " + bconf.toString() + " - entry found: '"+ entry.toString() + "'"));
             }

    }

    /**
     *
     * @param root Enum Constant for the root context of this config pack
     * @param possibleConfigFlag the allowed types of config flag for this
     * @return the file name and an array of the contained values
     * @param <Flag>
     */
    @Nullable
private <Flag extends ConfigFlag> HashMap<String, ArrayList<ConfigEntry>> getConfigEntriesForRoot
    (ConfigRoots root, Flag[] possibleConfigFlag){

        plugin.debug                                                                           (this,"Reading sub configurations from root folder " +plugin.configPath +root.folderPath);
        // initialize reference for reading directory
        File dir = new File(plugin.configPath + root.folderPath);
        //initialize output variable
        HashMap<String, ArrayList<ConfigEntry>> output = new HashMap<String, ArrayList<ConfigEntry>>();
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
                        f.getName().substring(0, f.getName().lastIndexOf(".")),
                        getConfigEntriesForFile(f, possibleConfigFlag));

            //END foreach
            // repeat for all yaml files in the folder, then return output.
        }
    // all files have been checked
        plugin.debug                                                                                    (this,"Initializing Building Config pack...", true);
        return output;
    }

@NotNull
private <T extends ConfigFlag> ArrayList<ConfigEntry> getConfigEntriesForFile
            (File f, T[] possibleConfigFlag){

        //read yamlConfiguration from file, init output value
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
        ArrayList<ConfigEntry> configContent = new ArrayList<>();

    //for each applicable config flag, look in the yaml for corresponding value.
        for(ConfigFlag flag : possibleConfigFlag) {
            //key is valid; now check if entry is valid.
            if (!f.getName().endsWith("yml"))
                continue;
            if(!flag.usesDefaults()) plugin.debug                                                   (this, "The config "+f.getName()+" is not set to use default values.");
            try {
                if(yaml.contains(flag.getYamlKey())
                && flag.getCondition().isFulfilledFor(yaml.get(flag.getYamlKey()))) {
            //conditions are met, parsing entry to output...
                    plugin.debug                                                                    (this, "The file "+f.getName()+" contains an entry for "+flag.getYamlKey()+", reading it...");
                    configContent.add(new ConfigEntry(flag, yaml.get(flag.getYamlKey())));
                } else //no valid entry found in yaml. checking if it should be set to default.
                if(flag.usesDefaults()) { //the "default" setting is stored in the respective ConfigFlag Enum.
                    configContent.add(new ConfigEntry(flag, flag.getDefaultValue()));
                    continue;
                }
                plugin.debug                                                                        (this, "No valid entry for "+f.getName()+" under the key "+flag.getYamlKey());
            //next iteration
            } catch (ConfigEntryClassMismatchException e) {
                throw new RuntimeException(e);}
        }
    //END foreach
/*
      ConfigEntry[] output = new ConfigEntry[configContent.size()+1];
        int i = 0;
        for(ConfigEntry centry : configContent) {
            output[i] = centry;
            i++;
        }
*/
    return configContent;
    }

// =========================================================================
// =========================================================
// ====================== Public Section
// ===============================
// ====================

    public void saveAll() {
        try {
            mainConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}