package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class StringPropertyTranslator implements PropertyTranslator<String> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{String.class};
    }

    @Override
    public String newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return (String) sourcePropValue;
        }

        if (sourcePropValue instanceof Enum) {
            return ((Enum) sourcePropValue).name();
        }

        return sourcePropValue.toString();
    }

    @Override
    public String translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue, targetType, targetPropType);
    }
}
