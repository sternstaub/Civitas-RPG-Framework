package io.github.sternstaub.civitasrpg.flags;

public enum LocaleFlag {
    PLUGIN_INITIALIZATION_COMPLETE("plugin.init_complete",
            "Initialization complete!")

    ;





    public String getDefaultMessage() {
        return defaultMessage;
    }

    final String defaultMessage;

    public final String yamlKey;
    LocaleFlag(String yamlKey, String defaultMessage) {
        this.yamlKey = yamlKey;
        this.defaultMessage = defaultMessage;
    }
}
