package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.flags.config.ConfigFlagBuilding;
import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlag;
import io.github.sternstaub.civitasrpg.flags.config.ConfigFlagsMain;
import io.github.sternstaub.civitasrpg.flags.config.ConfigFolderFlags;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static io.github.sternstaub.civitasrpg.CivitasRPG.PLUGIN;

public class ConfigFile<ABCDEConfigFlag extends AbstractConfigFlag> {
    protected static final CivitasRPG plugin = CivitasRPG.PLUGIN;

    protected final String filename;
    protected final ConfigFolderFlags root;
    protected final File file;
    protected final YamlConfiguration yaml;
    private HashMap<AbstractConfigFlag, ConfigEntry>
    flagsAndValues = new HashMap<AbstractConfigFlag, ConfigEntry>();

    public ConfigFile(String filename, ConfigFolderFlags root, ArrayList<ConfigEntry<?>> entries) {
        plugin.debug(this,"Initializing ConfigFile Class for file " +filename+".yml ...", true);
        this.filename = filename;
        this.root = root;

        this.file = new File(PLUGIN.configPath + root.folderPath
                + filename + ".yml");
        this.yaml = YamlConfiguration.loadConfiguration(file);

        if(entries.isEmpty()) {
            plugin.debug(this, "No entries passed, creating empty config file "+filename);
            return;
        }

        for(ConfigEntry entry : entries) {
            plugin.debug(this, entry.toString());
            flagsAndValues.put(entry.getFlag(), entry);
        }

        plugin.debug(this,"Done.");
    }


    //VOID
    /**
     * Method to save the currently cached yamlConfiguration
     * into the corresponding yml-file on disk.
     * @throws IOException in case the file cannot be saved for whatever reason.
     */
    public void save () throws IOException {
        plugin.debug(this, "Saving config file to "
                + file.getAbsolutePath());
        yaml.save(file);
    }
    public void addValue(ConfigEntry entry) {
            plugin.debug(this, "Adding '"+entry.getFlag()+"' with value '"+ entry.getValue()+"' to "+filename);
            flagsAndValues.put(entry.getFlag(), entry);
    }



    // #######################################
    // ###################### GETTERS #######
    // ###################################
    public ConfigEntry<?> getEntry(@NotNull ConfigFlagsMain mce) {
        return flagsAndValues.get(mce);
    }
    public Object getValueFor(@NotNull ConfigFlagsMain mce) {
        return flagsAndValues.get(mce).getValue();
    }
    @Nullable
    public Map<String, Object>
    getSubSectionValues(String subsection) {
        YamlConfiguration section = (YamlConfiguration)
                yaml.getConfigurationSection(subsection);
        if(section.getValues(false).isEmpty())
            return null;
        return section.getValues(false);
    }


    /**
     * Check if the loaded yamlConfig contains all the keys required by the plugin.
     * Values are stored in enum, so iterate over enum for different date types.
     * If entry is not contained in yml, add it.
     */

    public String getName() {
        return filename;
    }

    public String getFilename() {
        return filename;
    }

    public HashMap<AbstractConfigFlag, ConfigEntry> getFlagsAndValues() {
        return flagsAndValues;
    }

    public boolean isValid() {
        if(flagsAndValues.isEmpty())
            return false;
        return true;
    }

}
