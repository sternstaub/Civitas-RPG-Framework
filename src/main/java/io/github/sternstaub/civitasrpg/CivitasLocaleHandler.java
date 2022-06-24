package io.github.sternstaub.civitasrpg;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.flags.config.LocaleConfigFlag;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CivitasLocaleHandler {

    private static final CivitasRPG plugin = CivitasRPG.PLUGIN;
    private final File langFile;
    private final YamlConfiguration langYaml;
    final String localeFolderPath = CivitasRPG.PLUGIN.dataPath + "lang/";

    /**
     *
     * @param localeKey the language key for the locale to use, like "en" or "it", "es" etc.
     */
    public CivitasLocaleHandler(String localeKey) {
        // Load localized messages from yaml into the langYaml
        langFile = new File(localeFolderPath + localeKey + ".yml");
        langYaml = YamlConfiguration.loadConfiguration(langFile);
        if(langFile.mkdirs())
            plugin.log("The locale file for " + langFile.getPath() + " does not exist. Creating...");

        // Iterate through config options enum and check if one is missing in the yaml.
        for(LocaleConfigFlag loc : LocaleConfigFlag.values()) {
            if (!langYaml.contains(loc.yamlKey)) {

                //if there is no entry for a language key in the given Yaml, create it with a default value:
                plugin.log("No localization entry found for the key " + loc.yamlKey + ", filling in the english default...");
                langYaml.set(loc.yamlKey, loc.getDefaultMessage());
            }
        }

        //language file has been loaded.
        plugin.log("The localization '" + localeKey + "' was loaded.");

    }


    /*
      @return the message associated to the given lang-key, or the key (if no value can be found for it)
     */
    public String fetch(LocaleConfigFlag localeKey) {
        return langYaml.getString(localeKey.yamlKey);
    }



    public void save() throws IOException {
        langYaml.save(langFile);
    }

    private void loadDefaultLocale() {

    }
}
