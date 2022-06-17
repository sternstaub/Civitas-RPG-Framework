package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.config.main.CivitasMainConfig;
import io.github.sternstaub.civitasrpg.config.subconfig.civilization.CivitasCivilizationConfig;

public enum ConfigRoots {
    MAIN(new CivitasMainConfig(), false),

    CIVILIZATION(new CivitasCivilizationConfig(), true)

    ;

    public final CivitasConfigFile configfile;
    public final boolean isPack;
    ConfigRoots(CivitasConfigFile config, boolean isPack) {
        this.configfile = config;
        this.isPack = isPack;

    }

}
