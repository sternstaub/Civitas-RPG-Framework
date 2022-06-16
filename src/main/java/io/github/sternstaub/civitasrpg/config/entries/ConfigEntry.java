package io.github.sternstaub.civitasrpg.config.entries;

import io.github.sternstaub.civitasrpg.exceptions.ConfigEntryTypeMismatchException;

public class ConfigEntry {

    public final ConfigEntryType type;
    public final String key;
    public final Object defaultValue;

    ConfigEntry(String key, String standardValue) {
        this.key = key;
        this.defaultValue = standardValue;
        type = ConfigEntryType.STRING;
    }
    ConfigEntry(String key, Object standardValue) {
        this.key = key;
        this.defaultValue = standardValue;
        type = ConfigEntryType.INT;
    }
    ConfigEntry(String key, double standardValue) {
        this.key = key;
        this.defaultValue = standardValue;
        type = ConfigEntryType.DOUBLE;
    }

    /**
     *
     * @param configentrytype forces the programmer to specify the expected return type of the requested entry.
     * @return ConfigEntries for either String, int or double
     * @param <T> MUST be int, double or String!
     * @throws ConfigEntryTypeMismatchException when the given Type does not equal the expected type
     */
    protected <T> T getDefaultValue(ConfigEntryType configentrytype) throws ConfigEntryTypeMismatchException {

        if(type != configentrytype)
            throw new ConfigEntryTypeMismatchException(configentrytype, type);

        switch (type) {
            case INT:
                if(defaultValue instanceof Integer)
                    return (T) defaultValue;

            case DOUBLE:
                if(defaultValue instanceof Double)
                    return (T) defaultValue;
            case STRING:
                if(defaultValue instanceof String)
                    return (T) defaultValue;

        }
        return null;
    }
}
