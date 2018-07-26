package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
@SuppressWarnings("unchecked")
public class EnumPropertyTranslator implements PropertyTranslator<Enum<?>> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{Enum.class};
    }

    @Override
    public Enum<?> newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return Enum.valueOf((Class) targetPropType, (String) sourcePropValue);
        }

        if (targetPropType instanceof Class) {
            if (((Class) targetPropType).isAssignableFrom(sourcePropValue.getClass())) {
                return (Enum<?>) sourcePropValue;
            }
        }

        //if (sourcePropValue instanceof Number) -- 数字转Enum?

        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropValue.getClass() + ", targetPropClass: " + targetPropType);
    }

    @Override
    public Enum<?> translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue, targetType, targetPropType);
    }
}
