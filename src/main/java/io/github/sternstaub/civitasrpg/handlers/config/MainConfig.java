package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.flags.MainConfigFlag;
import io.github.sternstaub.civitasrpg.flags.RootConfigFlag;

public class MainConfig extends ConfigFile {

    // Class properties


    public MainConfig() {
        super(plugin.configPath, "config.yml", RootConfigFlag.MAIN);
        plugin.debug(this, "Loading config from path "+plugin.configPath+"config.yml");
    }




    /**
     * Check if the loaded yamlConfig contains all the keys required by the plugin.
     * Values are stored in enum, so iterate over enum for different date types.
     * If entry is not contained in yml, add it.
     * Inherited from ConfigFile
     */
    @Override
    public boolean checkIntegrityAndLoad() {
        plugin.log("Checking Integrity of main config...");

        // add default values for keys which are missing in config.yml
        for(MainConfigFlag ce : MainConfigFlag.values()) {
            // Conditions for jumping to the next config entry
            if (ce.hasCompatibleValueInYamlConfig(yamlConfig))                               //requested command entry belongs to another config pack
                continue; // to next iteration for next MainConfigFlag.

            Object defaultValue = ce.getDefaultValue();
            // if no valid entry can be read from given config.yml, overwrite.
            plugin.debug(this,"config.yml does not contain a valid key for "
                    + ce + ", setting to the default value of " + defaultValue);

            yamlConfig.set(ce.key, defaultValue);
        }
        plugin.debug(this,
                "Configuration integrity check finished successful for main config!");
        return true;

    }


}