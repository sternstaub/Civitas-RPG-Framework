package io.github.sternstaub.civitasrpg.config.mainconfig;

import io.github.sternstaub.civitasrpg.config.CivitasConfig;

import java.io.IOException;

public class CivitasMainConfig extends CivitasConfig {


    public CivitasMainConfig() {
        super(plugin.configPath, "config.yml");
    }

    @Override
    public String getConfigString() {
        return null;
    }

    /**
     * Check if the loaded yamlConfig contains all the keys required by the plugin.
     * Values are stored in enum, so iterate over enum for different date types.
     * If entry is not contained in yml, add it.
     */
    public void checkConfigIntegrity() {
        // ...do it for String values...
        for (MainConfigString cs : MainConfigString.values()) {
            if (!yamlConfig.contains(cs.entryPath))
                yamlConfig.set(cs.entryPath, cs.defaultValue);
        }

        // ...for int values...
        for (MainConfigInt ci : MainConfigInt.values()) {
            if (!yamlConfig.contains(ci.entryPath))
                yamlConfig.set(ci.entryPath, ci.defaultValue);
        }
        // ...for double values...
        for (MainConfigDouble cd : MainConfigDouble.values()) {
            if (!yamlConfig.contains(cd.entryPath))
                yamlConfig.set(cd.entryPath, cd.defaultValue);

        }


    }

    /**
     * Method to save the currently cached yamlConfiguration
     * into the corresponding yml-file on disk.
     * @throws IOException in case the file cannot be saved for whatever reason.
     */
    public void save () throws IOException {
        plugin.log("Saving config file to " + configFile.getAbsolutePath());
        yamlConfig.save(configFile);
    }

    public String get(MainConfigString confstring) {
        return yamlConfig.getString(confstring.entryPath);
    }
    public int get(MainConfigInt confint) {
        return yamlConfig.getInt((confint.entryPath));
    }
    public double get(MainConfigDouble confdouble) {
        return yamlConfig.getDouble((confdouble.entryPath));
    }
}