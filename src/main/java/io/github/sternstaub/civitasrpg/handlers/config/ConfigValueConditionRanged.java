package io.github.sternstaub.civitasrpg.handlers.config;

import io.github.sternstaub.civitasrpg.exceptions.ConditionParametersClassMismatchException;
import io.github.sternstaub.civitasrpg.exceptions.NumericConfigConditionOutOfBoundException;
import org.jetbrains.annotations.Nullable;

public class ConfigValueConditionRanged extends ConfigValueCondition {

    private Object[] applicableRange;

    /**
     * A ConfigValueCondition contains: rules which can be applied to objects that are read from yaml-configuration (string or numeric classes).
     *
     * @param targetClass The class to which a config value is parsable for this condition to be fulfilled. Possible values: "String.class", "Integer.class"...
     * @param range       An array containing the valid values for this condition. Must be of same class as the first parameter. If null, no range will be applied. numeric conditions will only read the first two parameters; string values will act as a whitelist.
     * @throws ConditionParametersClassMismatchException
     */
    public ConfigValueConditionRanged(Class targetClass, @Nullable Object[] range)
            throws ConditionParametersClassMismatchException,
            NumericConfigConditionOutOfBoundException {
        super(targetClass);
        this.applicableRange = range;

        for(Object o : range) {                                                      // if the range parameters are all of the correct class
        if(!classesAreEqual(o.getClass(), targetClass))
            throw new ConditionParametersClassMismatchException();
    }
        if(Number.class.isAssignableFrom(targetClass)) {                             // if the given class inherits from the Number class,
        if(range.length != 2) {                                                   // read the first two values of the array
            throw new NumericConfigConditionOutOfBoundException();                // numeric conditions must always be described in 2 values, min and max.
        }
        // everything OK
        Object[] output = new Object[2];
        output[0] = range[0];
        output[1] = range[1];
        this.applicableRange = output;
        plugin.debug(this, "Initialized numeric condition. Output values are of type "
                +output[0].getClass().getSimpleName()+" and "+output[1].getClass().getSimpleName()
                +". They are populated with the values " + output[0]+" and "+output[1]);
    } else if(classesAreEqual(targetClass, String.class)) {                                   // ===== if the given class is STRING =============
        //for String
        for(Object o : range) {                                                         //loop through given object condition array
            if(!classesAreEqual(o.getClass(), applicableClass))                          //  dear programmer, you should explicitly only use one class :) here is an exception for you...
                throw new ConditionParametersClassMismatchException();              // (the config param conditions are hardcoded so when these are thrown it is an error by programmer, not user.)
        }
        applicableRange = range; // everything OK
    } else throw new ConditionParametersClassMismatchException();               // Exception: wrong class types ...?? could not handle.
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
    public boolean isFulfilledForValueObject(Object input) {

        if(!super.isFulfilledForValueObject(input))
            return false;

        // ...parsing action - string and numeric =))
        if(this.isStringWhitelistCondition()) {                                     // these should be objects of string.
            for(Object o : applicableRange) {
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
