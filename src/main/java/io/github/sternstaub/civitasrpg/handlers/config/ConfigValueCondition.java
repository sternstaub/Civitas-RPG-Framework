package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import io.github.sternstaub.civitasrpg.exceptions.ConditionParametersClassMismatchException;
import io.github.sternstaub.civitasrpg.exceptions.NumericConfigConditionOutOfBoundException;

public class ConfigValueCondition {

    final Class<?> applicableClass;
    final static CivitasRPG plugin = CivitasRPG.INSTANCE;


    /**
     * A ConfigValueCondition contains: rules which can be applied to objects that are read from yaml-configuration (string or numeric classes).
     * @param targetClass The class to which a config value is parsable for this condition to be fulfilled. Possible values: "String.class", "Integer.class"...
     */
    public ConfigValueCondition(Class<?> targetClass) {

        this.applicableClass = targetClass;
    }

    /**
     * Checks if the given Object can be used for a Configuration Entry that has this value condition.
     * @param input an object to check on. Only use String.class, or numeric types like Integer.class!
     * @return true when the given object is a valid config entry for this condition
     */
    public boolean isFulfilledForValueObject(Object input) {
        if(!classesAreEqual(input.getClass(), applicableClass)) {
            plugin.debug(this,                                          "Class mismatch: "+input.getClass()+" and "+applicableClass);
            return false;                                                           // when the given object belongs to a different class than required, the condition is not met.
        }
        return true;
    }

    protected boolean classesAreEqual(Class<?> a, Class<?> b) {
        if(a.equals(b))
            return true;
        return false;
    }
    @Override
    public String toString() {
        return "Condition requires for Class "+ applicableClass.getCanonicalName();
    }

}

