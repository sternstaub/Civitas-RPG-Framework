package io.github.sternstaub.civitasrpg.flags;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.bukkit.configuration.file.YamlConfiguration;

public enum MainConfigFlag {

    // Entries used for the top-level configuration files, as listed in RootConfigFlag enum.
    // Each config root has a meta-config. this meta-config will be regenerated which holds
    // the available subfolders as sections / subsections, aswell as an

    //
    //
    // MAIN CONFIG
    //
    //
    LANGUAGE
            ("plugin.language", "en"),
    DEBUG
            ("plugin.debug", true),

    ;







    /*
        Enum initialization
     */

    // =========================================================
    // ====================== Variables
    // ===============================


    public final String key;
    private final Object defaultValue;

    // =========================================================
    // ====================== Constructor Section
    // ===============================


    MainConfigFlag(String key, Object standardValue) {

        this.key = key;
        this.defaultValue = standardValue;
    }
/*
    MainConfigFlag(RootConfigFlag configpacktype, String key, String standardValue) {
        this.configpack = configpacktype;
        this.key = key;
        this.defaultValue = standardValue;
        //this.type = ConfigValueType.STRING;
    }
    MainConfigFlag(RootConfigFlag configpacktype, String key, int standardValue) {
        this.configpack = configpacktype;
        this.key = key;
        this.defaultValue = standardValue;
        //this.type = ConfigValueType.INT;
    }
    MainConfigFlag(RootConfigFlag configpacktype, String key, double standardValue) {
        this.configpack = configpacktype;
        this.key = key;
        this.defaultValue = standardValue;
        //this.type = ConfigValueType.DOUBLE;
    }
    MainConfigFlag(RootConfigFlag configpacktype, String key, boolean standardValue) {
        this.configpack = configpacktype;
        this.key = key;
        this.defaultValue = standardValue;
        // this.type = ConfigValueType.BOOLEAN;
    }
 */

    // =========================================================
    // ====================== Getting Config values
    // ===============================

    /**
     * @return ConfigEntries for either String, int or double
     */
    public Object getDefaultValue(){

        CivitasRPG.INSTANCE.debug                                           (this,"Fetching default value for main config entry "+ this);
        return defaultValue;
//        if(defaultValue instanceof Boolean)
//           return (Boolean) defaultValue;
//        if(defaultValue instanceof Double)
//            return (Double) defaultValue;
//        if(defaultValue instanceof Integer)
//            return (Integer) defaultValue;
//        if(defaultValue instanceof String)
//            return (String) defaultValue;

    }

    public boolean hasCompatibleValueInYamlConfig(YamlConfiguration yconf) {
        if(!yconf.contains(key))
            return false;

        CivitasRPG.INSTANCE.debug(this,"Checking YAML config entry '"
                + this + "' for the given value of '"
                + yconf.get(key) + "', which is expected to belong to class "
                + yconf.get(key).getClass().getSimpleName());

        if(yconf.get(key).getClass().equals(defaultValue.getClass()))
            return true;
        return false;
    }
}
