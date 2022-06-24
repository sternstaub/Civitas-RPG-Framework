package io.github.sternstaub.civitasrpg.flags.config;

import io.github.sternstaub.civitasrpg.interfaces.CivitasFlag;

import static io.github.sternstaub.civitasrpg.CivitasRPG.PLUGIN;

public enum ConfigFolderFlags implements CivitasFlag {
    MAIN("", ConfigFlagsMain.class,
            "MainConfigRoot"),

    BUILDING("buildings/", ConfigFlagBuilding.class,
            "BuildingConfigRoot")

    ;

    public final String folderPath;
    private final Class requiredFlagsClass;
    private final String flagName;
    // public final boolean isPack;
    ConfigFolderFlags(String rootfolder, Class requiredFlagsClass, String flagName) {
        this.folderPath = rootfolder;
        this.requiredFlagsClass = requiredFlagsClass;
        this.flagName = flagName;
    }

    public Class getRequiredFlagsClass() {
        return requiredFlagsClass;
    }

    @Override
    public String getName() {
        return flagName;
    }
}
