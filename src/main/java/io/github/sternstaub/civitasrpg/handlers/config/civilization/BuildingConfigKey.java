package io.github.sternstaub.civitasrpg.handlers.config.civilization;

public enum BuildingConfigKey {
    BUILDING_TYPE(
            "type"),
    TEST("test")

    ;


    public final String path;

    BuildingConfigKey(String ymlPath) {
        this.path = ymlPath;
    }
}
