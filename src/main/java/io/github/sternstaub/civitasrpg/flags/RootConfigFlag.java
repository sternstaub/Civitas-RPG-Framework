package io.github.sternstaub.civitasrpg.flags;

import io.github.sternstaub.civitasrpg.CivitasRPG;

public enum RootConfigFlag {
    MAIN(""),

    BUILDINGS("buildings/")

    ;

    public final String folderPath;
    // public final boolean isPack;
    RootConfigFlag(String rootfolder) {
        this.folderPath = rootfolder;
        CivitasRPG.INSTANCE.debug(this, this.toString()+" wurde konstruiert.");
    }

}
