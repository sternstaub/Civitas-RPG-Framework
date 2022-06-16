package io.github.sternstaub.civitasrpg;

import io.github.sternstaub.civitasrpg.config.CivitasConfigLoader;
import io.github.sternstaub.civitasrpg.config.CivitasLocaleLoader;
import io.github.sternstaub.civitasrpg.config.ConfigString;
import io.github.sternstaub.civitasrpg.config.Locale;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class CivitasRPG extends JavaPlugin {

    public static CivitasRPG INSTANCE;
    public final CivitasLocaleLoader locale;
    public final CivitasConfigLoader config;
    public final String dataPath;

    //Init private fields




    public CivitasRPG() {
        // These two must stand first !
        INSTANCE = this;
        dataPath = this.getDataFolder().getPath() + "/";

        // Create config and lang manager instances
        this.config = new CivitasConfigLoader();
        locale = new CivitasLocaleLoader();
    }


    /**
     * When enabling the plugin...
     */
    @Override
    public void onEnable() {
        //Initialize config loader...
        config.initialize();

        //load the locale file given in the config
        String language = config.get(ConfigString.LANGUAGE);
        locale.buffer(language);
        log(locale.fetch(Locale.PLUGIN_INIT_CONFIG));
    }


    /**
     *  =================== onDisable() ======================
     */

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

    public void log(String message) {
        this.getLogger().log(Level.INFO, message);
    }
}
