package io.github.sternstaub.civitasrpg.handlers.config.flags;

import io.github.sternstaub.civitasrpg.handlers.config.conditions.ConfigEntryCondition;

public interface ConfigFlag  {
    ConfigEntryCondition getCondition();
    String getYamlKey();
    String getName();
    <T extends ConfigFlag> T getInstance();
    Object getDefaultValue();
    boolean usesDefaults();
}
