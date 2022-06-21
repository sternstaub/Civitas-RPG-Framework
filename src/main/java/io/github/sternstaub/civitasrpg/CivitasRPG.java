package io.github.sternstaub.civitasrpg;

import io.github.sternstaub.civitasrpg.handlers.CivitasConfigHandler;
import io.github.sternstaub.civitasrpg.handlers.localization.CivitasLocaleHandler;
import io.github.sternstaub.civitasrpg.handlers.config.flags.MainConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.localization.LocaleFlag;
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
        locale = new CivitasLocaleHandler();

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

    @Override
    public void onEnable() {
        // Config Loader is initialized in the constructor

        /*
        Then buffer the lang messages for given locale key  into the CivitasLocaleLoader so that the messages can be fetched via the plugin.
        The buffer() method also ensures that all missing message-localizations will be replaced by default ones.
         */
        String language = config.mainConfig.getString(
                MainConfigFlag.LANGUAGE);
        locale.buffer(language);

        // get debugging setting.
        // when true, the plugin will log more messages to console in various situations.
        isDebug = config.mainConfig.getBool(MainConfigFlag.DEBUG);
        debug(this, "Debugging is on.");

        // TODO : BuildingHandler.loadFromConfigurations(CivitasConfigHandler handler)

        log(locale(LocaleFlag.PLUGIN_INITIALIZATION_COMPLETE));
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

    public String locale(LocaleFlag loc) {
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
