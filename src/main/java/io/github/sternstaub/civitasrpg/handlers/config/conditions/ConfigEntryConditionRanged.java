package io.github.sternstaub.civitasrpg.handlers.config.conditions;

import io.github.sternstaub.civitasrpg.backend.exceptions.ConfigEntryClassMismatchException;
import io.github.sternstaub.civitasrpg.backend.exceptions.RangedConditionOutOfBoundException;
import org.jetbrains.annotations.Nullable;

public class ConfigEntryConditionRanged extends ConfigEntryCondition {

    private Object[] applicableRange;

    /**
     * A ConfigEntryCondition contains: rules which can be applied to objects that are read from yaml-configuration (string or numeric classes).
     *
     * @param range       An array containing the valid values for this condition. Must be of same class as the first parameter. If null, no range will be applied. numeric conditions will only read the first two parameters; string values will act as a whitelist.
     * @throws ConfigEntryClassMismatchException
     */
    @SuppressWarnings("unchecked")
    public ConfigEntryConditionRanged(@Nullable Object[] range) throws RangedConditionOutOfBoundException, ConfigEntryClassMismatchException {
        super(range.getClass().getComponentType());
        Class<?> givenclass = range.getClass().getComponentType();

        if(range.getClass().getComponentType().equals(String.class)) {
            applicableRange = range;
        }

        else if(Number.class.isAssignableFrom(range.getClass().getComponentType())) {                             // if the given class inherits from the Number class,
            if(range.length != 2) {                                                                         // read the first two values of the array
                throw new RangedConditionOutOfBoundException();                                     // numeric conditions must always be described in 2 values, min and max.
            }

            // everything OK, creating numeric condition
            java.lang.Object[] output = new java.lang.Object[2];
            output[0] = range[0];
            output[1] = range[1];
            this.applicableRange = (Object[]) output;
            plugin.debug                                                            (this, "Initialized numeric condition with the values " + output[0]+" and "+output[1]);
        } else throw new ConfigEntryClassMismatchException();               // Exception: wrong class types ...?? could not handle.

        this.applicableRange = range;



}

    /**
     *
     * @return if this condition uses a numeric class
     */
    public boolean isNumericCondition() {
        if(Number.class.isAssignableFrom(applicableClass))
            return true;
        return false;
    }
    public boolean isStringWhitelistCondition(){
        if(applicableRange[0].getClass().equals(String.class))
            return true;
        return false;
    }

@Override
    public boolean isFulfilledFor(java.lang.Object input) {

        if(!super.isFulfilledFor(input))
            return false;

        // ...parsing action - string and numeric =))
        if(this.isStringWhitelistCondition()) {                                     // these should be objects of string.
            for(java.lang.Object o : applicableRange) {
                String s = (String) o;
                if(s.equalsIgnoreCase((String) input))
                    return true;
            }                                                 //in case of string, the given input must match at least one of the objects from applicableRange array.
            plugin.debug(this, "String out of range.");
            return false;
        } else if(this.isNumericCondition()) {                                      // these should be numeric values
            double d1 = (Double) applicableRange[0];
            double d2 = (Double) applicableRange[1];
            double x = (Double) input;
            if(d1 <= x && x <= d2)
                return true;                                                        // input is within allowed range
            plugin.debug(this, "Numeric value out of range.");
            return false;                                                           // input is out of allowed range
        }
        return false;                                                               // something is wrong, this should actually not be called.
    }
    @Override
    public String toString() {
        String output = "REQUIRE TYPE "+ applicableClass.getSimpleName()+" IN RANGE: '";
        for(Object o : applicableRange) {
            output = output + o.toString() + "  ";
        }
        return output + "'";
    }
}
