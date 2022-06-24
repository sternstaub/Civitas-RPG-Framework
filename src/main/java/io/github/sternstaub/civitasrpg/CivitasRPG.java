package io.github.sternstaub.civitasrpg;

import io.github.sternstaub.civitasrpg.flags.config.ConfigFlagsMain;
import io.github.sternstaub.civitasrpg.flags.config.LocaleConfigFlag;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class CivitasRPG extends JavaPlugin {

    public static CivitasRPG PLUGIN;
    public final CivitasLocaleHandler locale;
    public final CivitasConfigHandler config;
    public final String dataPath;
    public final String configPath;

    private boolean isDebug = true;

    //Init private fields




    public CivitasRPG() {
        // These two must stand first !
        PLUGIN = this;
        dataPath = this.getDataFolder().getPath() + "/";
        configPath = dataPath + "config/";

        // Create config and lang manager instances
        // and link them to static variable

        this.config = new CivitasConfigHandler();
        locale = new CivitasLocaleHandler((String) config.mainConfig.getEntry(
                ConfigFlagsMain.LANGUAGE).getValue());

        this.getLogger().log(Level.INFO, "CivitasRPG main class initialized. \n" +
                "Data Path: " + dataPath + "\n" +
                "configPath: " + configPath);
    }


    // =========================================================
    // =========================================================
    // =========================================================
    // =========================================================
    // =========================================================
    // =========================================================
    // =========================================================
    // =========================================================
    // ====================== When enabling the plugin...
    // ===============================
    // ========================
    // ======================
    // =============

// (^ Config Loader is initialized in the constructor ^)
    @Override
    public void onEnable() {

        // get debugging setting.
        // when true, the plugin will log more messages to console in various situations.
        isDebug = (Boolean) config.mainConfig.getEntry(ConfigFlagsMain.DEBUG).getValue();
        debug(this, "Debugging is on.");

        // TODO : BuildingHandler.loadFromConfigurations(CivitasConfigHandler handler)

        log(locale(LocaleConfigFlag.PLUGIN_INITIALIZATION_COMPLETE));
    }


    // ================================================
    // =======================================================
    // ===============================================================
    // ===================================================================
    // ========================================================================
    // =======================================================================
    // ================================================================
    // =============================================================
    // ===========================================================
    // ==========================================================
    // ====================== When disabling the plugin...
    // ===============================
    // ========================
    // =================

    @Override
    public void onDisable() {
        try {
            locale.save();
            config.saveAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /*
    #################################################################################
    #################################################################################
    ############################## Override methods end #############################
    #################################################################################
    #################################################################################
     */



    /*
    ==========================================================================
    ============= LOCALIZATION
    ==========
     */

    public String locale(LocaleConfigFlag loc) {
        return locale.fetch(loc); }



    /*
    =========================================================================
    ============= logging and debugging stuff
    ==========
     */

    public void log(String message) {
        this.getLogger().log(Level.INFO, message);
    }

    public void debug(Object o, String message, Boolean isnewsection) {
        if(!isDebug)
            return;

        if(isnewsection)
            log("============================================");

        log("[DEBUG - Class " + o.getClass().getSimpleName() + "] "
                + message);
    }
    public void debug(Object o, String message) {
        if(!isDebug)
            return;;
        log("[DEBUG - " + o.getClass().getSimpleName() + "] "
                + message);
    }
}
