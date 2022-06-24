package io.github.sternstaub.civitasrpg.flags.config;

import io.github.sternstaub.civitasrpg.config.ConfigCondition;
import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlag;

import static io.github.sternstaub.civitasrpg.CivitasRPG.PLUGIN;

public enum ConfigFlagsMain implements AbstractConfigFlag {

    // Entries used for the main config.

    LANGUAGE(new ConfigCondition(String.class),
    "LanguageSetting", "en", "plugin.language"
    ),
    DEBUG(new ConfigCondition(Boolean.class),
    "DebugSetting", true, "plugin.debug"
    ),
    BACKUP_STORED_VALUES_ON_SAVE(new ConfigCondition(Boolean.class),
    "BackupSetting", false, "storage.backup"
    )
    ;

    // =========================================================
    // ====================== Variables
    // ===============================
    private final String key;
    private final String flagName;
    private final Object defaultValue;

    private final ConfigCondition condition;
    // =========================================================
    // ====================== Constructor Section
    // ===============================
    ConfigFlagsMain(ConfigCondition condition, String flagName, Object defaultValue, String yamlKey) {
        this.key = yamlKey;
        this.flagName = flagName;
        this.defaultValue = defaultValue;
        this.condition = condition;
    }

    // =========================================================
    // ====================== Getting Config values
    // ===============================
    /**
     * @return ConfigEntries for either String, int or double
     */

    @Override
    public Object getDefaultValue(){
        PLUGIN.debug                                           (this,"Fetching default value "+defaultValue+" for main config entry "+ this);
        return defaultValue;
    }

//    public boolean hasCompatibleValueInYamlConfig(YamlConfiguration yconf) {
//        if(!yconf.contains(key))
//            return false;
//
//        CivitasRPG.PLUGIN.debug(this,"Checking YAML config entry '"
//                + this + "' for the given value of '"
//                + yconf.get(key) + "', which is expected to belong to class "
//                + yconf.get(key).getClass().getSimpleName());
//
//        if(yconf.get(key).getClass().equals(defaultValue.getClass()))
//            return true;
//        return false;
//    }

    @Override
    public ConfigCondition getCondition() {
        return condition;
    }

    @Override
    public String getYamlKey() {
        return key;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public <T extends AbstractConfigFlag> T getInstance() {
        return (T) this;
    }
    @Override
    public boolean usesDefaults() {
        return true;
    }
}
