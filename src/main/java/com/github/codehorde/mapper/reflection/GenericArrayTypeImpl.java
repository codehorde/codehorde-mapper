package com.github.codehorde.mapper.reflection;    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class GenericArrayTypeImpl implements GenericArrayType, Serializable {
    private final Type componentType;

    public GenericArrayTypeImpl(Type componentType) {
        this.componentType = TypeUtils.canonicalize(componentType);
    }

    public Type getGenericComponentType() {
        return componentType;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GenericArrayType
                && TypeUtils.equals(this, (GenericArrayType) o);
    }

    @Override
    public int hashCode() {
        return componentType.hashCode();
    }

    @Override
    public String toString() {
        return TypeUtils.typeToString(componentType) + "[]";
    }

    private static final long serialVersionUID = 0;
}