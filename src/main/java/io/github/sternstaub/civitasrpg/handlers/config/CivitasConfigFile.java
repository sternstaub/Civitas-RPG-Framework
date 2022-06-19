package io.github.sternstaub.civitasrpg.handlers.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.jetbrains.annotations.NotNull;

public abstract class CivitasConfigFile {
    public final String configRootPath;
    public final String filename;
    public static final CivitasRPG plugin = CivitasRPG.INSTANCE;

    public final File file;
    public final YamlConfiguration yamlConfig;

    protected CivitasConfigFile(String configRootPath, String filename) {
        this.configRootPath = configRootPath;
        this.filename = filename;

        this.file = new File(configRootPath + filename);
        this.yamlConfig = YamlConfiguration.loadConfiguration(file);
    }
    /**
     * load the configuration from yaml and check if it contains all the required config keys
     */
    public abstract boolean checkIntegrityAndLoad();


    // #######################################
    // ###################### GETTERS #######
    // ###################################
    public String getString(@NotNull ConfigValue mce) {
        return yamlConfig.getString(mce.key);
    }
    public int getInt(@NotNull ConfigValue mce) {
        return yamlConfig.getInt(mce.key);
    }
    public double getDouble(@NotNull ConfigValue mce) {
        return yamlConfig.getDouble(mce.key);
    }

    public boolean getBool(@NotNull ConfigValue mce) {
        return yamlConfig.getBoolean(mce.key);
    }

    /**
     * Method to save the currently cached yamlConfiguration
     * into the corresponding yml-file on disk.
     * @throws IOException in case the file cannot be saved for whatever reason.
     */
    public void save () throws IOException {
        plugin.debug(this, "Saving config file to "
                + file.getAbsolutePath());
        if(this instanceof CivitasConfigPack) {
            CivitasConfigPack pack = (CivitasConfigPack) this;
            plugin.debug(this,
                    "Found config pack with a root named " + pack.root.toString());
            for(CivitasConfigFile ccf : pack.getPackSubConfigFiles()) {
                ccf.save();
                plugin.debug(this,
                        "Saving subconfig" + ccf.filename);
            }
        }
        yamlConfig.save(file);
    }

}
