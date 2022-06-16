package io.github.sternstaub.civitasrpg.config;

public enum ConfigString {
    LANGUAGE("plugin.language", "en");

    final String entryPath;
    final String defaultValue;
    ConfigString(String configPath, String defaultValue) {
        this.entryPath = configPath;
        this.defaultValue = defaultValue;
    }
}
