package io.github.sternstaub.civitasrpg.config.main;

import io.github.sternstaub.civitasrpg.config.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CivitasMainConfig extends CivitasConfigFile{

    // Class properties


    private final ConfigRoots root = ConfigRoots.MAIN;
    public CivitasMainConfig() {

        super(plugin.configPath, "config.yml");
    }




    /**
     * Check if the loaded yamlConfig contains all the keys required by the plugin.
     * Values are stored in enum, so iterate over enum for different date types.
     * If entry is not contained in yml, add it.
     * Inherited from CivitasConfigFile
     */
    @Override
    public void checkIntegrityAndLoad() {
        plugin.log("Checking Integrity of main config...");

        // add default values for keys which are missing in config.yml
        for(ConfigEntry ce : ConfigEntry.values()) {

            // Conditions for jumping to the next config entry
            if (ce.hasCompatibleValueInYamlConfig(yamlConfig)
                || ce.configpack != ConfigRoots.MAIN) //requested command entry belongs to another config pack
                continue// to next iteration for next ConfigEntry.
                 ;
            Object defaultValue = ce.getDefaultValue();

            // if no valid entry can be read from given config.yml, overwrite.
            plugin.debug("config.yml does not contain a valid key for "
                    + ce + ", setting to the default value of " + defaultValue);

            yamlConfig.set(ce.key, defaultValue);
        }

        plugin.debug("Configuration integrity check finished successful!");
    }


    /**
     * Method to save the currently cached yamlConfiguration
     * into the corresponding yml-file on disk.
     * @throws IOException in case the file cannot be saved for whatever reason.
     */
    public void save () throws IOException {
        plugin.log("Saving config file to " + file.getAbsolutePath());
        yamlConfig.save(file);
    }


    // #######################################
    // ###################### GETTERS #######
    // ###################################
    public String getString(@NotNull ConfigEntry mce) {
        return yamlConfig.getString(mce.key);
    }
    public int getInt(@NotNull ConfigEntry mce) {
        return yamlConfig.getInt(mce.key);
    }
    public double getDouble(@NotNull ConfigEntry mce) {
        return yamlConfig.getDouble(mce.key);
    }

    public boolean getBool(@NotNull ConfigEntry mce) {
        return yamlConfig.getBoolean(mce.key);
    }

}