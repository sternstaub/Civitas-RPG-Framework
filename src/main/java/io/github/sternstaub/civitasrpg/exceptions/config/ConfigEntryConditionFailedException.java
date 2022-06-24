package io.github.sternstaub.civitasrpg.exceptions.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.exceptions.CivitasException;
import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlag;

public class ConfigEntryConditionFailedException extends CivitasException {


    private final AbstractConfigFlag key;


    private final Object value;
    public ConfigEntryConditionFailedException(AbstractConfigFlag configKey, Object value) {
        this.key = configKey;
        this.value = value;
        CivitasRPG.PLUGIN.log("ERROR: Condition check failed for config key " + configKey.getName()
                +". Expected "+configKey.getCondition().getRequiredClass().getSimpleName()
                + ", but found " + value.getClass().getSimpleName());
    }

    public AbstractConfigFlag getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
}
