package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CivitasConfigLoader {

    private static final CivitasRPG plugin = CivitasRPG.INSTANCE;
    public CivitasConfigLoader() {
    }

    private YamlConfiguration config;
    private File configFile;

    public void initialize() {
        configFile = new File(plugin.dataPath + "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        // Check if all the required configuration keys are in the yaml
        for(ConfigString cs : ConfigString.values()) {
            // If given config.yml does not contain the desired string value,
            // write the default into it.
            if(!config.contains(cs.entryPath))
                config.set(cs.entryPath, cs.defaultValue);
        }
        for(ConfigInt ci : ConfigInt.values()) {
            if(!config.contains(ci.entryPath))
                config.set(ci.entryPath, ci.defaultValue);
        }
    }

    public void save() throws IOException {
        plugin.log("Saving config file to " + configFile.getAbsolutePath());
        config.save(configFile);
    }

    public String get(ConfigString confstring) {
        return config.getString(confstring.entryPath);
    }
    public int get(ConfigInt confint) {
        return config.getInt((confint.entryPath));
    }
}
