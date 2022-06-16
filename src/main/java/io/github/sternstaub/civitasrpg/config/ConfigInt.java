package io.github.sternstaub.civitasrpg.config;

public enum ConfigInt {
    MENGE_BROT("plugin.brot", 1);


    final String entryPath;
    final int defaultValue;

    /**
     *
     * @param entryPath path of the configuration entry, as given in yaml-files
     * @param defaultValue the default value of the configuration entry
     */
    ConfigInt(String entryPath, int defaultValue) {
        this.entryPath = entryPath;
        this.defaultValue = defaultValue;
    }
}
