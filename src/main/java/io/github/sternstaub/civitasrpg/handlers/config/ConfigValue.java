package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.bukkit.configuration.file.YamlConfiguration;

public enum ConfigValue {

    // Entries used for the top-level configuration files, as listed in ConfigRoot enum.
    // Each config root has a meta-config. this meta-config will be regenerated which holds
    // the available subfolders as sections / subsections, aswell as an

    //
    //
    // MAIN CONFIG
    //
    //
    LANGUAGE
            (ConfigRoot.MAIN,
            "plugin.language", "en"),
    DEBUG
            (ConfigRoot.MAIN,

            "plugin.debug", true),
    NAME_DES_FREUNDES
            (ConfigRoot.MAIN,

            "beta.freund_name","klaus der dreizehnte"),
    MENGE_BROT
            (ConfigRoot.MAIN,

            "plugin.brot", 1),



    //
    //
    // CONFIG PACKS
    //
    //

    FOLDER_NAME_BUILDING_CONFIG
            (ConfigRoot.BUILDINGS,
            "civilization.config.folders.buildings", "buildings/");







    /*
        Enum initialization
     */

    // =========================================================
    // ====================== Variables
    // ===============================


    // public final ConfigValueType type;
    public final ConfigRoot configpack;
    public final String key;
    private final Object defaultValue;

    // =========================================================
    // ====================== Constructor Section
    // ===============================

    ConfigValue(ConfigRoot configpacktype, String key, String standardValue) {
        this.configpack = configpacktype;
        this.key = key;
        this.defaultValue = standardValue;
        //this.type = ConfigValueType.STRING;
    }
    ConfigValue(ConfigRoot configpacktype, String key, int standardValue) {
        this.configpack = configpacktype;
        this.key = key;
        this.defaultValue = standardValue;
        //this.type = ConfigValueType.INT;
    }
    ConfigValue(ConfigRoot configpacktype, String key, double standardValue) {
        this.configpack = configpacktype;
        this.key = key;
        this.defaultValue = standardValue;
        //this.type = ConfigValueType.DOUBLE;
    }
    ConfigValue(ConfigRoot configpacktype, String key, boolean standardValue) {
        this.configpack = configpacktype;
        this.key = key;
        this.defaultValue = standardValue;
        // this.type = ConfigValueType.BOOLEAN;
    }

    // =========================================================
    // ====================== Getting Config values
    // ===============================

    /*
     * @param configentrytype forces the programmer to specify the expected return type of the requested entry.
     * @return ConfigEntries for either String, int or double
     * @param Object MUST be int, double or String!
     * @throws ConfigEntryTypeMismatchException when the given Type does not equal the expected type
     */
    public Object getDefaultValue(){

        CivitasRPG.INSTANCE.debug(this,"Fetching default value for config entry "
                + this + " in configuration pack " + configpack);
        if(defaultValue instanceof Boolean)
            return (Boolean) defaultValue;
        if(defaultValue instanceof Double)
            return (Double) defaultValue;
        if(defaultValue instanceof Integer)
            return (Integer) defaultValue;
        if(defaultValue instanceof String)
            return (String) defaultValue;

        return defaultValue;
    }

    public boolean hasCompatibleValueInYamlConfig(YamlConfiguration yconf) {
        if(!yconf.contains(key))
            return false;

        CivitasRPG.INSTANCE.debug(this,"Checking YAML config entry '"
                + this + "' from the config pack '"
                + this.configpack + "' for compatibility with fetched yaml value '"
                + yconf.get(key) + "', which is expected to belong to class "
                + yconf.get(key).getClass().getSimpleName());

        if(yconf.get(key).getClass().getName().equalsIgnoreCase(
                defaultValue.getClass().getName()))
            return true;
        return false;
    }
}
