package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.handlers.config.civilization.BuildingConfigMeta;

public enum ConfigRoot {
    MAIN(""),

    BUILDINGS("buildings/")

    ;

    public final String folderPath;
    // public final boolean isPack;
    ConfigRoot(String rootfolder) {
        this.folderPath = rootfolder;
        CivitasRPG.INSTANCE.debug(this, this.toString()+" wurde konstruiert.");
    }

}
