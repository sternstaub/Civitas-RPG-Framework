package io.github.sternstaub.civitasrpg;

import io.github.sternstaub.civitasrpg.config.CivitasConfigFile;
import io.github.sternstaub.civitasrpg.config.CivitasConfigPack;
import io.github.sternstaub.civitasrpg.config.ConfigRoots;
import io.github.sternstaub.civitasrpg.config.main.CivitasMainConfig;
import io.github.sternstaub.civitasrpg.config.subconfig.civilization.CivitasCivilizationConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CivitasConfigHandler {

    private static final CivitasRPG plugin = CivitasRPG.INSTANCE;

    // loading all the civilization configs which could be found and validated
    // mapping the building identifiers to the CivitasConfig objects.

    public static  CivitasConfigFile MAINCONFIG;
    public static HashMap<ConfigRoots, CivitasConfigFile> CONFIGPACKS
            = new HashMap<ConfigRoots, CivitasConfigFile>();


    // ========================================================================
    // =================================================
    // ====================== Initialization
    // ==================================
    // ======================

    public CivitasConfigHandler() {

        MAINCONFIG = ConfigRoots.MAIN.configfile;
        MAINCONFIG.checkIntegrityAndLoad();

        // CivitasConfigurationFiles have a method to load the
        // .yml files from their respective folders.
        // it is called checkIntegrityAndLoad().
        // This will also check if all the required configuration keys
        // are represented in the yml files.
        // missing entries will be set to a default value.
        // The root configurations are described by an enumeration,
        // while some of the root files will look for further
        // files in specified subdirectories.
        for(ConfigRoots root : ConfigRoots.values()) {
            CivitasRPG.INSTANCE.debug("Registering configuration root " + root);
            if(root == ConfigRoots.MAIN) continue;
            if(root.configfile instanceof CivitasConfigPack) {
                CONFIGPACKS.put(root, root.configfile);
                CONFIGPACKS.get(root).checkIntegrityAndLoad();
            }
        }
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

    public void save() {
        try {
            MAINCONFIG.save();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}