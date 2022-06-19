package io.github.sternstaub.civitasrpg.handlers.config;

public enum LocaleEntry {
    PLUGIN_INITIALIZATION_COMPLETE("plugin.init_complete",
            "Initialization complete!")

    ;





    public String getDefaultMessage() {
        return defaultMessage;
    }

    final String defaultMessage;

    public final String yamlKey;
    LocaleEntry(String yamlKey, String defaultMessage) {
        this.yamlKey = yamlKey;
        this.defaultMessage = defaultMessage;
    }
}
