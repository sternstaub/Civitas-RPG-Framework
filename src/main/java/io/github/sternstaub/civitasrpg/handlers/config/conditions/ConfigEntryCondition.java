package io.github.sternstaub.civitasrpg.handlers.config.conditions;

import io.github.sternstaub.civitasrpg.CivitasRPG;

import javax.annotation.Nullable;

public class ConfigEntryCondition {

    final Class<?> applicableClass;
    final static CivitasRPG plugin = CivitasRPG.PLUGIN;


    /**
     * A ConfigEntryCondition contains: rules which can be applied to objects that are read from yaml-configuration (string or numeric classes).
     * @param targetClass The class to which a config value is parsable for this condition to be fulfilled. Possible values: "String.class", "Integer.class"...
     */
    public ConfigEntryCondition(Class<?> targetClass) {

        this.applicableClass = targetClass;
    }

    public ConfigEntryCondition(Object o) {
        this.applicableClass = o.getClass();
    }

    /**
     * Checks if the given Object can be used for a Configuration Entry that has this value condition.
     * @param input an object to check on. Only use String.class, or numeric types like Integer.class!
     * @return true when the given object is a valid config entry for this condition
     */
    public boolean isFulfilledFor(@Nullable Object input) {
        if(input == null) {
            plugin.debug(this,"Condition check failed because the input object was null.");
            return false;
        }
        if(!classesAreEqual(input.getClass(), applicableClass)) {
            plugin.debug(this,"Class mismatch: Input '"+input.getClass()+"' does not fit "+applicableClass);
            return false;                                                           // when the given object belongs to a different class than required, the condition is not met.
        }
        return true;
    }
    public boolean supportsClassType(Class clazz) {
        if(applicableClass.equals(clazz))
            return true;
        return false;
    }

    public Class<?> getRequiredClass() {
        return applicableClass;
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

