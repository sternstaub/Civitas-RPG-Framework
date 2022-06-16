package io.github.sternstaub.civitasrpg.config.subconfig.civilization;

import io.github.sternstaub.civitasrpg.config.CivitasConfig;

public class CivilizationObjectConfig extends CivitasConfig {
    protected CivilizationObjectConfig(String configRootPath, String filename) {
        super(plugin.configPath + "civilization/", filename);
    }

    // protected CivilizationObjectConfig() {
        //super(plugin.configPath + "civilization/", yamlConfig, configFile);
    //}

    @Override
    public String getConfigString() {
        return null;
    }

    @Override
    public void checkConfigIntegrity() {

    }
}
