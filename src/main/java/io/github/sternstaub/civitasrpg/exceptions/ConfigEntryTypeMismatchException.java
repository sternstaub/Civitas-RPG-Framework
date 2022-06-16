package io.github.sternstaub.civitasrpg.exceptions;

import io.github.sternstaub.civitasrpg.config.entries.ConfigEntryType;

public class ConfigEntryTypeMismatchException extends Exception {
    public final ConfigEntryType type1;
    public final ConfigEntryType type2;

    public ConfigEntryTypeMismatchException(ConfigEntryType type1, ConfigEntryType type2) {
        this.type1 = type1;
        this.type2 = type2;
    }
}
