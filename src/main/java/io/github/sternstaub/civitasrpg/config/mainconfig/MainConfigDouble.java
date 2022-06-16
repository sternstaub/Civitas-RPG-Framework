package io.github.sternstaub.civitasrpg.config.mainconfig;

/**
 * Enum to store all configuration keys and their corresponding default values.
 * Only contains Double values, as opposed to ConfigInt and ConfigString.
 */
public enum MainConfigDouble {
    ;
    public final String entryPath;
    public final double defaultValue;
    MainConfigDouble(String entryPath, double defaultValue) {
        this.entryPath = entryPath;
        this.defaultValue = defaultValue;
    }
}
