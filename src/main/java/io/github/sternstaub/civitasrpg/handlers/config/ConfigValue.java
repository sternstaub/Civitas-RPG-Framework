package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.exceptions.ConditionParametersClassMismatchException;
import io.github.sternstaub.civitasrpg.exceptions.NumericConfigConditionOutOfBoundException;

import java.io.ObjectInputFilter;

public class ConfigValue {
    private Object value;
    public ConfigValue(Object value, Class<?> conditionType) throws ConditionParametersClassMismatchException {
        ConfigValueCondition condition = new ConfigValueCondition(conditionType); //this entry has the condition to be of certain type (String, Integer, Double..)
        if(!condition.isFulfilledForValueObject(value))
            throw new ConditionParametersClassMismatchException();
        this.value = value;

    }
    public ConfigValue(Object value, Object[] range) throws ConditionParametersClassMismatchException, NumericConfigConditionOutOfBoundException {
        ConfigValueCondition condition = new ConfigValueConditionRanged(value.getClass(), range);
        if(!condition.isFulfilledForValueObject(value))
            throw new ConditionParametersClassMismatchException();
        this.value = value;
    }
    public ConfigValue(Object value, ConfigValueCondition condition) throws ConditionParametersClassMismatchException {
        if(!condition.isFulfilledForValueObject(value))
            throw new ConditionParametersClassMismatchException();
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
