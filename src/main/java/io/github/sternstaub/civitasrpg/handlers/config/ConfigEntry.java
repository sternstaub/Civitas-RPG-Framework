package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.backend.exceptions.ConfigEntryClassMismatchException;
import io.github.sternstaub.civitasrpg.handlers.config.flags.ConfigFlag;

public class ConfigEntry {
    private Object value;
    private ConfigFlag key;
    public ConfigEntry(ConfigFlag key, Object value) throws ConfigEntryClassMismatchException {
        if(!key.getCondition().isFulfilledFor(value))
            throw new ConfigEntryClassMismatchException();

        this.value = value;
        this.key = key;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        if(!key.getCondition().isFulfilledFor(value))
            return;
        this.value = value;
    }
    public boolean containsClass(Class c) {
        if(c.equals(value.getClass()))
            return true;
        return false;
    }
    public ConfigFlag getFlag() {
        return key;
    }


    @Override
    public String toString() {
        return key.toString() + " >>> "+ value.toString();
    }
}
