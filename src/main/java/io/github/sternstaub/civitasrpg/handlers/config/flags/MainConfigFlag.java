package io.github.sternstaub.civitasrpg.handlers.config.flags;

import io.github.sternstaub.civitasrpg.handlers.config.conditions.ConfigEntryCondition;

import static io.github.sternstaub.civitasrpg.CivitasRPG.PLUGIN;

public enum MainConfigFlag implements ConfigFlag {

    // Entries used for the main config.

    LANGUAGE("plugin.language",
    "en"),
    DEBUG("plugin.debug",
    true),
    BACKUP_STORED_VALUES_ON_SAVE("storage.backup",
    false)
    ;

    // =========================================================
    // ====================== Variables
    // ===============================
    private final String key;
    private final Object defaultValue;

    private final ConfigEntryCondition condition;
    // =========================================================
    // ====================== Constructor Section
    // ===============================
    MainConfigFlag(String key, Object value) {
        this.key = key;
        this.defaultValue = value;
        PLUGIN.debug(this, "Initializing Constant. Input: "+key+" and value "+value+" of "+value.getClass());
        if(value.getClass().equals(Boolean.class)) {
            this.condition = new ConfigEntryCondition(Boolean.class);
        } else
            this.condition = new ConfigEntryCondition(key);
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
    public ConfigEntryCondition getCondition() {
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
    public <T extends ConfigFlag> T getInstance() {
        return (T) this;
    }
    @Override
    public boolean usesDefaults() {
        return true;
    }
}
