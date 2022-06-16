package io.github.sternstaub.civitasrpg.config.mainconfig;

/**
 * Enum to store configuration keys and their corresponding default values.
 * Only contains Int values, as opposed to ConfigString and ConfigDouble.
 */
public enum MainConfigInt {
    MENGE_BROT("plugin.brot", 1);


    final String entryPath;
    final int defaultValue;

    /**
     *
     * @param entryPath path of the configuration entry, as given in yaml-files
     * @param defaultValue the default value of the configuration entry
     */
    MainConfigInt(String entryPath, int defaultValue) {
        this.entryPath = entryPath;
        this.defaultValue = defaultValue;
    }
}
