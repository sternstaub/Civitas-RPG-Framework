package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.handlers.config.flags.ConfigRoots;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Implemented by ConfigFiles which are supposed to have a subfolder
 * attached to them
 */
public class ConfigPack {

    protected final ArrayList<ConfigFile> subConfigs;
    protected final ConfigRoots root;
    public static CivitasRPG plugin = CivitasRPG.PLUGIN;


    public ConfigPack(ConfigRoots root, ArrayList<ConfigFile> subConfigs) {
        this.root = root;
        this.subConfigs = subConfigs;


    }


    public void
    addSubConfig(ConfigFile subConfig) {
        subConfigs.add(subConfig);
    }
    public ArrayList<ConfigFile>
    getSubConfigs() {
        return subConfigs;
    }

    public void registerSubConfigs() {

    }

    public void
    saveAll() throws IOException {
        plugin.debug                                                            (this, "Saving configurations for " + this.getClass().getSimpleName());
        for(ConfigFile ccf : subConfigs) {
            ccf.save();
        }
    }

}
