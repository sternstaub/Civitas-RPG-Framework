package io.github.sternstaub.civitasrpg.handlers.config;

import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implemented by ConfigFiles which are supposed to have a subfolder
 * attached to them
 */
public abstract class CivitasConfigPack extends CivitasConfigFile {
    
    private final List<CivitasConfigFile> subConfigs;
    protected final ConfigRoot root;
    public CivitasConfigPack(ConfigRoot root) {
        // Create meta-config file from superclass,
        // as configPack inherits from ConfigFile.
        super(plugin.configPath + root.folderPath, "_meta.yml");
        this.root = root;
        this.subConfigs = getPackSubConfigFiles();
    }

    public List<CivitasConfigFile> getSubConfigurations() {
        return subConfigs;
    }
    public void saveAll() throws IOException {
        for(CivitasConfigFile ccf : subConfigs) {
            ccf.save();
        }
    }

    @Override
    public void save() throws IOException {
        // save config-pack meta.yml
        super.save();
        // save all files within the subfolder
        saveAll();
    }



    public ArrayList <YamlConfiguration> getYamlConfigsFromFolder(){
                                                                                                plugin.debug(this,"Reading sub configurations from root folder " +plugin.configPath +root.folderPath);
        File dir = new File(plugin.configPath + root.folderPath);
        ArrayList <YamlConfiguration> output = new ArrayList<>();

        if(!dir.exists() || dir.listFiles() == null) {
                                                                                                plugin.debug(this, "directory of "+dir.getAbsolutePath()+ " does not exist. Creating it, but reading no configurations...");
            try {
                dir.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return output;
        }


        YamlConfiguration yconf = new YamlConfiguration();
        for(File f : dir.listFiles()) {
            if(f.getName().endsWith("yml") && !f.getName().equalsIgnoreCase(
                            plugin.config.META_FILE_NAME))
                yconf = YamlConfiguration.loadConfiguration(f);
                yconf.set("name", f.getName().substring(0, f.getName().lastIndexOf(".")));  //cutting the extension from the file name);

                output.add(yconf);                                                              // Because YamlConfiguration does not hold the file name, it will be written into the "name" key of the files!
                                                                                                plugin.debug(this, "Loaded " + f.getAbsolutePath());
        }
        //return all yaml files in the folder except the meta-conf.

        return output;
    }

    @Nullable
    protected  abstract List<CivitasConfigFile> getPackSubConfigFiles();

    protected abstract boolean isCompatibleWithYaml(YamlConfiguration yconf);


}
