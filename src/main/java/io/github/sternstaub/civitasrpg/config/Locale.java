package io.github.sternstaub.civitasrpg.config;

public enum Locale {
    PLUGIN_INIT_CONFIG("plugin.init.config", "Initializing Config...");





    public String getDefaultMessage() {
        return defaultMessage;
    }

    String defaultMessage;

    public String getYamlKey() {
        return yamlKey;
    }

    String yamlKey;
    Locale(String yamlKey, String defaultMessage) {
        this.yamlKey = yamlKey;
        this.defaultMessage = defaultMessage;
    }
}
