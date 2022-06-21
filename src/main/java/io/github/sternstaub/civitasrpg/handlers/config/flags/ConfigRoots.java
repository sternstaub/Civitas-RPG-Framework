package io.github.sternstaub.civitasrpg.handlers.config.flags;

import io.github.sternstaub.civitasrpg.CivitasRPG;

public enum ConfigRoots {
    MAIN("", MainConfigFlag.class),

    BUILDINGS("buildings/", BuildingConfigFlag.class)

    ;

    public final String folderPath;
    private final Class requiredFlagsClass;
    // public final boolean isPack;
    ConfigRoots(String rootfolder, Class requiredFlagsClass) {
        this.folderPath = rootfolder;
        this.requiredFlagsClass = requiredFlagsClass;
        CivitasRPG.PLUGIN.debug(this, this.toString()+" wurde konstruiert.");
    }

    public Class getRequiredFlagsClass() {
        return requiredFlagsClass;
    }

}
