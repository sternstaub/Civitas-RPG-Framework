package io.github.sternstaub.civitasrpg.handlers;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.flags.BuildingConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigFile;
import io.github.sternstaub.civitasrpg.handlers.config.MainConfig;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigValue;
import io.github.sternstaub.civitasrpg.handlers.config.building.BuildingConfig;
import io.github.sternstaub.civitasrpg.handlers.config.building.BuildingConfigPack;

import java.io.IOException;
import java.util.Map;

public class CivitasConfigHandler {

    private static final CivitasRPG plugin = CivitasRPG.INSTANCE;
    public static final String META_FILE_NAME = "_meta.yml";

// loading all the civilization configs which could be found and validated
// mapping the building identifiers to the CivitasConfig objects.

    public static ConfigFile MAINCONFIG;
    public static BuildingConfigPack PACK_BUILDINGCONFIG;


// ========================================================================
// =================================================
// ====================== Initialization
// ==================================
// ======================

    // CivitasConfigurationFiles have a method to load the .yml files from disk and parse their content into java objects.
// it is called checkIntegrityAndLoad().
// This will also check if all the required configuration keys are represented in the yml files.
// Missing entries will be set to a default value.
    public CivitasConfigHandler() {
        MAINCONFIG = new MainConfig();
        if(!MAINCONFIG.checkIntegrityAndLoad())
            plugin.log("Main Configuration integrity could not be verified!");
        plugin.log("Main Configuration loaded.");

        PACK_BUILDINGCONFIG = new BuildingConfigPack();

        for(BuildingConfig bconf : PACK_BUILDINGCONFIG.getRegisteredBuildingConfigs()) {
            for(Map.Entry<BuildingConfigFlag, ConfigValue> entry: bconf.getConfigKeyValues().entrySet()) {
                plugin.debug(this,"Building Config " +bconf.toString()
                        +" contains entry:" + entry.getKey() + " " + entry.getValue().toString());
            }
        }

    }

// =========================================================================
// =========================================================
// ====================== Public Section
// ===============================
// ====================

    public void saveAll() {
        try {
            MAINCONFIG.save();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}