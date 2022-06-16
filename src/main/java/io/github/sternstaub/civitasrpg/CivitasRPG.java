package io.github.sternstaub.civitasrpg;

import io.github.sternstaub.civitasrpg.config.CivitasConfigLoader;
import io.github.sternstaub.civitasrpg.config.CivitasLocaleLoader;
import io.github.sternstaub.civitasrpg.config.mainconfig.MainConfigString;
import io.github.sternstaub.civitasrpg.config.mainconfig.Locale;
import jdk.jfr.internal.LogLevel;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class CivitasRPG extends JavaPlugin {

    public static CivitasRPG INSTANCE;
    public final CivitasLocaleLoader locale;
    public final CivitasConfigLoader config;
    public final String dataPath;
    public final String configPath;

    //Init private fields




    public CivitasRPG() {
        // These two must stand first !
        INSTANCE = this;
        dataPath = this.getDataFolder().getPath() + "/";

        configPath = dataPath + "config/";

        // Create config and lang manager instances
        // and link them to static variable

        this.config = new CivitasConfigLoader();
        locale = new CivitasLocaleLoader();

        this.getLogger().log(Level.INFO, "CivitasRPG loaded. \n" +
                "Data Path: " + dataPath + "\n" +
                "configPath: " + configPath);
    }


    // =========================================================================
    // =========================================================
    // ====================== When enabling the plugin...
    // ===============================
    // ====================


    @Override
    public void onEnable() {
        //Initialize config loader...

        //load the locale key (like en, nl, de...) as configured by user
        String language = config.getMainConfigString(
                MainConfigString.LANGUAGE);

        // buffer the lang messages into the localeLoader
        // so that the messages can be fetched by the plugin
        // buffer() also ensures that all missing message-localizations
        // will be replaced by default ones.
        locale.buffer(language);

        // print the initialization
        log(getMessage(Locale.PLUGIN_INITIALIZATION_COMPLETE));
    }


    // =========================================================================
    // =========================================================
    // ====================== When disabling the plugin...
    // ===============================
    // ====================

    @Override
    public void onDisable() {
        try {
            locale.save();
            config.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  ====================================
     *  ================ inheritance end ===
     *  ====================================
     */

    public String getMessage(Locale loc) {
        return locale.fetch(loc); }

    public void log(String message) {
        this.getLogger().log(Level.INFO, message);
    }
}
