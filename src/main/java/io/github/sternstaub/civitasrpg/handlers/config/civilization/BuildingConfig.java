package io.github.sternstaub.civitasrpg.handlers.config.civilization;

import io.github.sternstaub.civitasrpg.handlers.config.CivitasConfigFile;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigRoot;

import java.util.EnumMap;
import java.util.HashMap;

public class BuildingConfig extends CivitasConfigFile {

    protected BuildingConfig(String filename) {

        super(ConfigRoot.BUILDINGS.folderPath, filename);
        // initialize the main pack config (this class) by giving a file

    }

    @Override
    public boolean checkIntegrityAndLoad(){

        for(BuildingConfigKey bck : BuildingConfigKey.values()) {
            if(!yamlConfig.contains(bck.path)) {
                return false;
            }}
        //

        return true;
    }

}
