package io.github.sternstaub.civitasrpg.handlers;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.handlers.config.CivitasConfigFile;
import io.github.sternstaub.civitasrpg.handlers.config.CivitasMainConfig;
import io.github.sternstaub.civitasrpg.handlers.config.civilization.BuildingConfigMeta;

import java.io.IOException;

public class CivitasConfigHandler {

    private static final CivitasRPG plugin = CivitasRPG.INSTANCE;
    public static final String META_FILE_NAME = "_meta.yml";

// loading all the civilization configs which could be found and validated
// mapping the building identifiers to the CivitasConfig objects.

    public static  CivitasConfigFile MAINCONFIG;
    public static BuildingConfigMeta PACK_BUILDINGCONFIG;


// ========================================================================
// =================================================
// ====================== Initialization
// ==================================
// ======================

    public CivitasConfigHandler() {


// CivitasConfigurationFiles have a method to load the .yml files from disk and parse their content into java objects.
// it is called checkIntegrityAndLoad().
// This will also check if all the required configuration keys are represented in the yml files.
// Missing entries will be set to a default value.

        MAINCONFIG = new CivitasMainConfig();
        MAINCONFIG.checkIntegrityAndLoad();
        CivitasRPG.INSTANCE.debug(this,"Main Configuration loaded.");

        PACK_BUILDINGCONFIG = new BuildingConfigMeta();
        PACK_BUILDINGCONFIG.checkIntegrityAndLoad();

    }


// ========================================================================
// =================================================
// ====================== Private Task Section
// ==================================
// ======================



// =========================================================================
// =========================================================
// ====================== Public Section
// ===============================
// ====================

    public void saveAll() {
        try {
            MAINCONFIG.save();
            PACK_BUILDINGCONFIG.save();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}