package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public abstract class CivitasConfig {
    public final String configRootPath;
    public final String filename;
    public static final CivitasRPG plugin = CivitasRPG.INSTANCE;

    public final File configFile;
    public final YamlConfiguration yamlConfig;

    protected CivitasConfig(String configRootPath, String filename) {
        this.configRootPath = configRootPath;
        this.filename = filename;

        this.configFile = new File(configRootPath + filename);
        this.yamlConfig = YamlConfiguration.loadConfiguration(configFile);
    }

    public abstract String getConfigString();
    public abstract void checkConfigIntegrity();
}
