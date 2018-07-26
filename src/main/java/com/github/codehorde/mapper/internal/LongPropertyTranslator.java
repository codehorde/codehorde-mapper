package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class LongPropertyTranslator implements PropertyTranslator<Long> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{Long.class, long.class};
    }

    @Override
    public Long newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return Long.parseLong((String) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return ((Number) sourcePropValue).longValue();
        }

        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropValue.getClass() + ", targetPropClass: " + targetPropType);
    }

    @Override
    public Long translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue,  targetType,  targetPropType);
    }
}
