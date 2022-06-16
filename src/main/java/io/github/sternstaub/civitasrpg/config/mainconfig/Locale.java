package io.github.sternstaub.civitasrpg.config.mainconfig;

public enum Locale {
    PLUGIN_INITIALIZATION_COMPLETE("plugin.init_complete",
            "Initialization complete!")

    ;





    public String getDefaultMessage() {
        return defaultMessage;
    }

    final String defaultMessage;

    public final String yamlKey;
    Locale(String yamlKey, String defaultMessage) {
        this.yamlKey = yamlKey;
        this.defaultMessage = defaultMessage;
    }
}
