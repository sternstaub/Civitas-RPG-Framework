package io.github.sternstaub.civitasrpg.flags;

import io.github.sternstaub.civitasrpg.exceptions.ConditionParametersClassMismatchException;
import io.github.sternstaub.civitasrpg.exceptions.NumericConfigConditionOutOfBoundException;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigValueCondition;
import io.github.sternstaub.civitasrpg.handlers.config.ConfigValueConditionRanged;

import javax.annotation.Nullable;

/**
 * any building configuration file needs to hold all of these keys
 * the reason for this is that each building object will have exactly one setting for each of these entries
 */
public enum BuildingConfigFlag {
    BUILDING_TYPE(
            "type",
            String.class,
            new String[]{"anchor", "default"}),
    TEST(
            "upgradable",
            Boolean.class,
            null)

    ;


    public final String path;
    public ConfigValueCondition condition;
    public final Class type;



    BuildingConfigFlag(String ymlPath, Class type, @Nullable Object[] conditions) {
        this.path = ymlPath;
        this.type = type;
        if(conditions == null) {
            this.condition = new ConfigValueCondition(type);
        } else {
            try {
                this.condition = new ConfigValueConditionRanged(type, conditions);
            } catch (ConditionParametersClassMismatchException e) {
                throw new RuntimeException(e);
            } catch (NumericConfigConditionOutOfBoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
