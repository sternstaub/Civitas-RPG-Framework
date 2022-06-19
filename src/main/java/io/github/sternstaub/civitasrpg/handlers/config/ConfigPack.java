package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.flags.RootConfigFlag;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implemented by ConfigFiles which are supposed to have a subfolder
 * attached to them
 */
public abstract class ConfigPack {

    protected final ArrayList<ConfigFile> subConfigs = new ArrayList<>();
    protected final RootConfigFlag root;
    public static CivitasRPG plugin = CivitasRPG.INSTANCE;
    public ConfigPack(RootConfigFlag root) {
        this.root = root;

        // Create meta-config file from superclass,
        // as configPack inherits from ConfigFile.
    }


    public abstract void saveAll() throws IOException;



    public ArrayList <YamlConfiguration> getYamlConfigsFromFolder(){
                                                                                                plugin.debug(this,"Reading sub configurations from root folder " +plugin.configPath +root.folderPath);
        File dir = new File(plugin.configPath + root.folderPath);
        ArrayList <YamlConfiguration> output = new ArrayList<>();

        // if there is no dir, we dont need to do anything. no config can be loaded. empty list will return.
        if(!dir.exists() || dir.listFiles() == null) {
            plugin.log                                             ("Subconfig directory "+dir.getAbsolutePath()+ " does not exist. Creating it, but reading no configurations...");
            dir.mkdirs();
            return output;
        }


        for(File f : dir.listFiles()) {
            YamlConfiguration yconf = YamlConfiguration.loadConfiguration(f);

            if ((f.getName().endsWith("yml") || f.getName().endsWith("yaml")) && !f.getName().contains(plugin.config.META_FILE_NAME)) {        // Because YamlConfiguration does not hold the file name, it will be written into the "name" key of the files!
                plugin.debug                     (this, "Loading " + f.getAbsolutePath());
                yconf.set("name", f.getName().substring(0, f.getName().lastIndexOf(".")));              // while cutting the extension from the file name);
                output.add(yconf);
                }
            //return all yaml files in the folder except the meta-conf.
        }
        return output;
    }

    public List<ConfigFile> getSubConfigurations() {
        return subConfigs;
    }

}
