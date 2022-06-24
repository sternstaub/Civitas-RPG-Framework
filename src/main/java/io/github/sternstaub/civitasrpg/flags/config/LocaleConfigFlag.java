package io.github.sternstaub.civitasrpg.flags.config;

public enum LocaleConfigFlag {
    PLUGIN_INITIALIZATION_COMPLETE("plugin.init_complete",
            "Initialization complete!")

    ;





    public String getDefaultMessage() {
        return defaultMessage;
    }

    final String defaultMessage;

    public final String yamlKey;
    LocaleConfigFlag(String yamlKey, String defaultMessage) {
        this.yamlKey = yamlKey;
        this.defaultMessage = defaultMessage;
    }
}
