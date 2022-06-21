package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.handlers.config.flags.BuildingConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.config.flags.ConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.config.flags.MainConfigFlag;
import io.github.sternstaub.civitasrpg.handlers.config.flags.ConfigRoots;
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

public class ConfigFile<ABCDEConfigFlag extends ConfigFlag> {
    protected static final CivitasRPG plugin = CivitasRPG.PLUGIN;

    protected final String configRootPath;
    protected final String filename;
    protected final ConfigRoots root;
    protected final File file;
    protected final YamlConfiguration yaml;
    private HashMap<ConfigFlag, ConfigEntry>
            flagsAndValues = new HashMap<ConfigFlag, ConfigEntry>();

    public ConfigFile(String filename, ConfigRoots root, ArrayList<ConfigEntry> entries) {
        plugin.debug(this,"Initializing ConfigFile Class for file " +filename+"...", true);
        this.configRootPath = root.folderPath;
        this.filename = filename;
        this.root = root;

        this.file = new File(configRootPath + filename);
        this.yaml = YamlConfiguration.loadConfiguration(file);
        plugin.debug(this, "Länge array: "+entries.size());

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
    public void addValue(ConfigEntry input, ABCDEConfigFlag flag) {

        if(input.getFlag().getClass().equals(flag.getClass())) {
            plugin.debug(this, "Adding '"+input.getFlag()+"' with value '"+input.getValue()+"' to "+filename);

            flagsAndValues.put((BuildingConfigFlag) input.getFlag(), input);
            return;
        }
        PLUGIN.debug(this, "Failed to add value to config file named '" + filename + "'.");
    }



    // #######################################
    // ###################### GETTERS #######
    // ###################################
    public String getString(@NotNull MainConfigFlag mce) {
        return (String) getFlagsAndValues().get(mce).getValue();
    }
    public  int getInt (@NotNull MainConfigFlag mce) {
        return (int) getFlagsAndValues().get(mce).getValue();
    }
    public double getDouble(@NotNull MainConfigFlag mce) {
        return (double) getFlagsAndValues().get(mce).getValue();
    }
    public Boolean getBool(@NotNull MainConfigFlag mce) {
        return (Boolean) getFlagsAndValues().get(mce).getValue();
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

    public HashMap<ConfigFlag, ConfigEntry> getFlagsAndValues() {
        return flagsAndValues;
    }

}
