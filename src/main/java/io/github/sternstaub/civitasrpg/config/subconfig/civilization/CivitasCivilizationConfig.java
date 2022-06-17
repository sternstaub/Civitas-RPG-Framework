package io.github.sternstaub.civitasrpg.config.subconfig.civilization;

import io.github.sternstaub.civitasrpg.config.CivitasConfigFile;
import io.github.sternstaub.civitasrpg.config.CivitasConfigPack;
import io.github.sternstaub.civitasrpg.config.ConfigEntry;

import java.io.IOException;
import java.util.List;

public class CivitasCivilizationConfig extends CivitasConfigFile implements CivitasConfigPack {
    public CivitasCivilizationConfig() {
        super(plugin.configPath + "civilization/",
                "civilization_structures.yml");
    }


    @Override
    public List<CivitasConfigFile> getSubConfigurations() {
        return null;
    }

    @Override
    public String getRootFolderPath() {
        return null;
    }

    @Override
    public void save() throws IOException {

    }


    @Override
    public void checkIntegrityAndLoad() {

    }

    @Override
    public int getInt(ConfigEntry ce) {
        return 0;
    }

    @Override
    public double getDouble(ConfigEntry ce) {
        return 0;
    }

    @Override
    public boolean getBool(ConfigEntry ce) {
        return false;
    }

    @Override
    public String getString(ConfigEntry ce) {
        return null;
    }
}
