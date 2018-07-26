package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class BooleanPropertyTranslator implements PropertyTranslator<Boolean> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{Boolean.class, boolean.class};
    }

    @Override
    public Boolean newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof Boolean) {
            return ((Boolean) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return ((Number) sourcePropValue).intValue() == 1;
        }

        if (sourcePropValue instanceof String) {
            return Boolean.valueOf((String) sourcePropValue);
        }

        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropValue.getClass() + ", targetPropClass: " + targetPropType);
    }

    @Override
    public Boolean translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue,  targetType,  targetPropType);
    }
}
