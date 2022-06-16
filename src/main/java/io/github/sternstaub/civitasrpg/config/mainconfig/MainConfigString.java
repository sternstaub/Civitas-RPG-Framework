package io.github.sternstaub.civitasrpg.config.mainconfig;

/**
 * Enum to store configuration keys and their corresponding default values.
 * Only contains String values, as opposed to ConfigInt and ConfigDouble.
 */
public enum MainConfigString {
    LANGUAGE("plugin.language", "en"),
    NAME_DES_FREUNDES("beta.freund_name","klaus der dreizehnte");

    final String entryPath;
    final String defaultValue;
    MainConfigString(String configPath, String defaultValue) {
        this.entryPath = configPath;
        this.defaultValue = defaultValue;
    }
}
