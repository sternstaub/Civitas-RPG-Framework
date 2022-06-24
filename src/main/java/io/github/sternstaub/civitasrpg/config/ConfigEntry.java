package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.exceptions.config.ConfigEntryConditionFailedException;
import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlag;

public class ConfigEntry<XyzConfigFlag extends AbstractConfigFlag> {
    private Object value;
    private XyzConfigFlag key;
    public ConfigEntry(XyzConfigFlag key, Object value) throws ConfigEntryConditionFailedException {
        if(!key.getCondition().isFulfilledFor(value))
            throw new ConfigEntryConditionFailedException(key, value);
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
    public AbstractConfigFlag getFlag() {
        return key;
    }


    @Override
    public String toString() {
        return key.toString() + " >>> "+ value.toString();
    }
}
