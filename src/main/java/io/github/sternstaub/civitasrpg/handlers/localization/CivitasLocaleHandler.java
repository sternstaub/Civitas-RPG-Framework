package io.github.sternstaub.civitasrpg.handlers.localization;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CivitasLocaleHandler {

    private static final CivitasRPG plugin = CivitasRPG.PLUGIN;
    private File langFile;
    private YamlConfiguration langYaml;
    final String localeFolderPath = CivitasRPG.PLUGIN.dataPath + "lang/";

    public CivitasLocaleHandler() {

    }


    /*
      @return the message associated to the given lang-key, or the key (if no value can be found for it)
     */
    public String fetch(LocaleFlag localeKey) {
        return langYaml.getString(localeKey.yamlKey);
    }


    /*
     * Load the
     * @param localeKey The key for the locale to use, like en = english. Actually, it's just the name of the yaml file to use. Will be fetched from config.
     */
    public void buffer(String localeKey) {

        // Load localized messages from yaml into the langYaml
        langFile = new File(localeFolderPath + localeKey + ".yml");
        langYaml = YamlConfiguration.loadConfiguration(langFile);

        // Iterate through config options enum and check if one is missing in the yaml.
        for(LocaleFlag loc : LocaleFlag.values()) {
            if (!langYaml.contains(loc.yamlKey)) {

                //if there is no entry for a language key in the given Yaml, create it with a default value:
                plugin.log("No localization entry found for the key " + loc.yamlKey + ", filling in the english default...");
                langYaml.set(loc.yamlKey, loc.getDefaultMessage());
            }
        }

        //language file has been loaded.
        plugin.log("The localization '" + localeKey + "' was loaded.");
    }

    public void save() throws IOException {
        langYaml.save(langFile);
    }

    private void loadDefaultLocale() {

    }
}
