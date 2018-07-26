package com.github.codehorde.mapper.reflection;    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * The WildcardType interface supports multiple upper bounds and multiple
 * lower bounds. We only support what the Java 6 language needs - at most one
 * bound. If a lower bound is set, the upper bound must be Object.class.
 */
public class WildcardTypeImpl implements WildcardType, Serializable {
    private final Type upperBound;
    private final Type lowerBound;

    public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
        ValidationUtils.checkArgument(lowerBounds.length <= 1);
        ValidationUtils.checkArgument(upperBounds.length == 1);

        if (lowerBounds.length == 1) {
            ValidationUtils.checkNotNull(lowerBounds[0]);
            TypeUtils.checkNotPrimitive(lowerBounds[0]);
            ValidationUtils.checkArgument(upperBounds[0] == Object.class);
            this.lowerBound = TypeUtils.canonicalize(lowerBounds[0]);
            this.upperBound = Object.class;

        } else {
            ValidationUtils.checkNotNull(upperBounds[0]);
            TypeUtils.checkNotPrimitive(upperBounds[0]);
            this.lowerBound = null;
            this.upperBound = TypeUtils.canonicalize(upperBounds[0]);
        }
    }

    public Type[] getUpperBounds() {
        return new Type[]{upperBound};
    }

    public Type[] getLowerBounds() {
        return lowerBound != null ? new Type[]{lowerBound} : TypeUtils.EMPTY_TYPE_ARRAY;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof WildcardType
                && TypeUtils.equals(this, (WildcardType) other);
    }

    @Override
    public int hashCode() {
        // this equals Arrays.hashCode(getLowerBounds()) ^ Arrays.hashCode(getUpperBounds());
        return (lowerBound != null ? 31 + lowerBound.hashCode() : 1)
                ^ (31 + upperBound.hashCode());
    }

    @Override
    public String toString() {
        if (lowerBound != null) {
            return "? super " + TypeUtils.typeToString(lowerBound);
        } else if (upperBound == Object.class) {
            return "?";
        } else {
            return "? extends " + TypeUtils.typeToString(upperBound);
        }
    }

    private static final long serialVersionUID = 0;
}