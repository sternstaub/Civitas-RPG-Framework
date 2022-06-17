package io.github.sternstaub.civitasrpg.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.List;

/**
 * Implemented by ConfigFiles which are supposed to have a subfolder attached to them
 */
public interface CivitasConfigPack {

    public abstract List<CivitasConfigFile> getSubConfigurations();
    public abstract String getRootFolderPath();
    public abstract void save() throws IOException;
}
