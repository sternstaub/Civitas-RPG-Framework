package io.github.sternstaub.civitasrpg.flags.interfaces.config;

import io.github.sternstaub.civitasrpg.config.ConfigCondition;
import io.github.sternstaub.civitasrpg.interfaces.CivitasFlag;

public interface AbstractConfigFlag extends CivitasFlag {
    ConfigCondition <Object> getCondition();
    String getYamlKey();
    String getName();
    <T extends AbstractConfigFlag> T getInstance();
    Object getDefaultValue();
    boolean usesDefaults();
}
