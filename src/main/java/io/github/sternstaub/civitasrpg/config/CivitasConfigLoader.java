package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.config.mainconfig.CivitasMainConfig;
import io.github.sternstaub.civitasrpg.config.mainconfig.MainConfigDouble;
import io.github.sternstaub.civitasrpg.config.mainconfig.MainConfigInt;
import io.github.sternstaub.civitasrpg.config.mainconfig.MainConfigString;

import java.io.IOException;

public class CivitasConfigLoader {

    private static final CivitasRPG plugin = CivitasRPG.INSTANCE;

    // TODO HashMap<String, CivitasCivilizationConfig> civilizationConfigs;
    // loading all the civilization configs which could be found and validated
    // mapping the building identifiers to the CivitasConfig objects.

    // the main / meta configuration file
    public final CivitasMainConfig mainConfig;

    // ========================================================================
    // =================================================
    // ====================== Constructor section
    // ==================================
    // ======================

    public CivitasConfigLoader() {
        this.mainConfig = new CivitasMainConfig();

        // reading routine for configuration subfolders,
        // like CivitasRPG/config/civilization.
        // will read and register all valid configurations
        // found within these folders.
        //
        readConfigSubFolders();

        // Check if all the required configuration keys
        // are represented in the yml file.
        // If given config.yml does not contain the desired value,
        // write the default into it.
        //
        checkConfigIntegrityOnRegisteredConfigs();

    }


    // ========================================================================
    // =================================================
    // ====================== Private Task Section
    // ==================================
    // ======================


    private void readConfigSubFolders() {

    }

    /**
     * checks if the registered config files are holding all required keys
     */
    private void checkConfigIntegrityOnRegisteredConfigs() {
        mainConfig.checkConfigIntegrity();
    }





    // =========================================================================
    // =========================================================
    // ====================== Public Section
    // ===============================
    // ====================


    public void save() {
        try {
            mainConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getMainConfigInt(MainConfigInt mci) {
        return mainConfig.get(mci);
    }
    public String getMainConfigString(MainConfigString mcs) {
        return mainConfig.get(mcs);
    }
    public double getMainConfigDouble(MainConfigDouble mcd) {
        return mainConfig.get(mcd);
    }

}
