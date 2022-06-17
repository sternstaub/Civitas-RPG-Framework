package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

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
    public abstract void checkIntegrityAndLoad();
    public abstract int getInt(ConfigEntry ce);
    public abstract double getDouble(ConfigEntry ce);

    public abstract boolean getBool(ConfigEntry ce);
    public abstract String getString(ConfigEntry ce);
    public abstract void save() throws IOException;

}
