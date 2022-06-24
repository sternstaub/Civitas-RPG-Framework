package io.github.sternstaub.civitasrpg.config;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * A class to store requirements for configuration entries. like "must be of type string",
 * or "must be String and equal X, Y or Z...
 */
public class ConfigCondition<V> {

    @Nullable private final Class<?> applicableClass;
    final static CivitasRPG plugin = CivitasRPG.PLUGIN;
    private final ArrayList<V> applicableValues = new ArrayList<V>();
    private int rangeLow = 0;
    private int rangeHigh = 0;

    /**
     *
     * @param value a singular value that will be required for this condition to be fulfilled
     */
    public ConfigCondition(V value) {
        if(value.getClass().equals(String.class))
            value = (V) ((String) value).toLowerCase();
        this.applicableClass = value.getClass();
        this.applicableValues.add(value);
    }
    /**
     *
     * @param values a set of values. for the condition to be fulfilled, the input of
     *               isFulfilledFor() needs to match one of those.
     */
    public ConfigCondition(V[] values) {
        this.applicableClass = values.getClass().getComponentType();
        for(V value : values) {
            if(value.getClass().equals(String.class))
                value = (V) ((String) value).toLowerCase();
            applicableValues.add(value);
        }
    }

    /**
     * Constructor for a ranged condition (has to lie between two numbers)
     * @param v1 minimum
     * @param v2 maximum
     */
    public ConfigCondition(int v1, int v2) {
        this.applicableClass = null;
        rangeLow= v1;
        rangeHigh = v2;
    }
    /**
     * when constructed without applicable values, will only check for class match.
     * @param clazz the class that values must belong to to fulfill the condition.
     *              Choose classes like "Integer", "Boolean" or "String".
     *              Please read the isFulfilledFor method for more info.
     */
    public ConfigCondition(@NotNull Class<?> clazz) {
        this.applicableClass = clazz;
    }

//
//
// ============== ^ Constructor ^ ===========

// ============== v Methods v ===============
//
//

/**
 * a method to add a value to the set of accepted values.
 * @param value the whitelisted value to add
 */
    public void addAcceptedValue(V value) {
        assert this.applicableClass != null;
        if(!this.applicableClass.equals(value.getClass()))
            return;
        this.applicableValues.add(value);
    }

/**
 * Checks if the given Object can be used for a Configuration Entry that has this value condition.
 * @param input an object to check on. Only use String, or numeric types like Integer!
 * @return true when the given object is a valid config entry for this condition
 */
    public boolean isFulfilledFor(@NotNull V input) {

            plugin.debug(this, "Checking input '" + input +"' of class "+ input.getClass().getSimpleName() +"..."
                    + " Numeric range: "+this.hasNumericRange() + " Assignable from number: " +input.getClass().isAssignableFrom(Number.class));

            //if a numeric value within a range is required (rangeLow and rangeHigh default to 0 if not set)
            if(this.hasNumericRange() && input instanceof Integer) {
                plugin.debug(this, "Numeric range found, applying to given value...");
                var d = (int) input;
                // if input is at least the low and at max the high value
                if(rangeLow <= d && rangeHigh >= d)
                    return true; // numeric value lies within range
            }

            //check if the class of the passed value matches the condition.
            if(!this.supportsClassType(input.getClass())) {
                plugin.debug(this, "Class mismatch: Input '" + input + "' does not fit " + this.toString());
                return false; //...if not, the condition is NOT met.
            }

            if (input.getClass().equals(String.class))
                input = (V) ((String) input).toLowerCase(); //this class holds strings only in lower case

            //check if the condition expects the value to match one of the applicableValues
            if(!this.applicableValues.isEmpty()) {
                //check if the input matches one of the
                if(applicableValues.contains(input))
                    return true; // matching value found.
                plugin.debug(this, "ConfigCondition failed: there is a whitelist, and the value " + input + " does not match it.");
                return false; // the input
            }
            return true;
    }

/**
 *
 * @param clazz the class to check on
 * @return true if this condition supports given type
 *
 */
    private boolean supportsClassType(Class<?> clazz) {
        // applicable class is only set to null when a numeric range condition is applied.
        if(applicableClass == null) {
            //in that case, input must be a number, and RangeLow must differ from rangeHigh.
            //input is not a number, or the condition does not hold a numeric range.
            return this.hasNumericRange() && clazz.isAssignableFrom(Number.class); //input is a number, and this is a ranged numeric condition.
        }
        else // if applicable class != null
            return applicableClass.equals(clazz); //if class is accepted -> true
    }

    public Class<?> getRequiredClass() {
        return applicableClass;
    }
    private boolean hasNumericRange() {
        return rangeLow < rangeHigh;
    }
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("C O N D I T I O N {");
        if(applicableClass != null)
            output.append(" assumes CLASS: ").append(applicableClass.getSimpleName());
        if(!applicableValues.isEmpty()){
            output.append(" >> assumes VALUES: ");
            for(V o : applicableValues) {
                output.append(o);
        }}
        if(this.hasNumericRange())
            output.append(" >> assumes a INTEGER which lies between MIN ").append(rangeLow).append(" and MAX ").append(rangeHigh);

        return output + " }";
    }

}

