package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.flags.config.ConfigFolderFlags;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Implemented by ConfigFiles which are supposed to have a subfolder
 * attached to them
 */
public class ConfigPack {

    protected final ArrayList<ConfigFile> subConfigs;
    protected final ConfigFolderFlags root;
    public static CivitasRPG plugin = CivitasRPG.PLUGIN;


    public ConfigPack(ConfigFolderFlags root, ArrayList<ConfigFile> subConfigs) {
        this.root = root;
        this.subConfigs = subConfigs;
    }
    public ConfigPack(ConfigFolderFlags root) {
        this.root = root;
        this.subConfigs = new ArrayList<ConfigFile>();
    }


    public void
    addSubConfig(ConfigFile subConfig) {
        if(subConfig.isValid())
            subConfigs.add(subConfig);
    }
    public ArrayList<ConfigFile>
    getSubConfigs() {
        return subConfigs;
    }


    public void
    saveAll() throws IOException {
        plugin.debug                                                            (this, "Saving configurations for " + this.getClass().getSimpleName());
        for(ConfigFile ccf : subConfigs) {
            ccf.save();
        }
    }

}
