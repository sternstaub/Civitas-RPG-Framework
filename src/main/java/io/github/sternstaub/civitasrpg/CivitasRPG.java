package io.github.sternstaub.civitasrpg;

import io.github.sternstaub.civitasrpg.config.ConfigEntry;
import io.github.sternstaub.civitasrpg.config.LocaleEntry;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class CivitasRPG extends JavaPlugin {

    public static CivitasRPG INSTANCE;
    public final CivitasLocaleHandler locale;
    public final CivitasConfigHandler configloader;
    public final String dataPath;
    public final String configPath;

    private boolean isDebug = true;

    //Init private fields




    public CivitasRPG() {
        // These two must stand first !
        INSTANCE = this;
        dataPath = this.getDataFolder().getPath() + "/";

        configPath = dataPath + "config/";

        // Create config and lang manager instances
        // and link them to static variable

        this.configloader = new CivitasConfigHandler();
        locale = new CivitasLocaleHandler();

        this.getLogger().log(Level.INFO, "CivitasRPG loaded. \n" +
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

    @Override
    public void onEnable() {
        // Config Loader is initialized in the constructor

        // First, load the locale key (like en, nl, de...) as configured by user.
        String language = configloader.MAINCONFIG.getString(
                ConfigEntry.LANGUAGE);

        /*
        Then buffer the lang messages for given locale key  into the CivitasLocaleLoader so that the messages can be fetched via the plugin.
        The buffer() method also ensures that all missing message-localizations will be replaced by default ones.
         */
        locale.buffer(language);

        // get debugging setting.
        // when true, the plugin will log more messages to console
        // in various situations.
        isDebug = configloader.MAINCONFIG.getBool(ConfigEntry.DEBUG);

        log(locale(LocaleEntry.PLUGIN_INITIALIZATION_COMPLETE));
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
            configloader.save();
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

    public String locale(LocaleEntry loc) {
        return locale.fetch(loc); }



    /*
    =========================================================================
    ============= logging and debugging stuff
    ==========
     */

    public void log(String message) {
        this.getLogger().log(Level.INFO, message);
    }

    public void debug(String message) {
        if(isDebug) {
            log("[DEBUG] " + message);
        }
    }
}
