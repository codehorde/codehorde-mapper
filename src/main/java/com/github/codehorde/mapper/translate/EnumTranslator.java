package com.github.codehorde.mapper.translate;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by baomingfeng at 2018-04-28 12:57:39
 */
public class EnumTranslator implements PropertyTranslator<Enum<?>> {

    @Override
    public Enum<?> translate(Object sourcePropValue, Type targetPropType) {
        Class targetPropClass = (Class) targetPropType;
        if (sourcePropValue instanceof String) {
            return Enum.valueOf(targetPropClass, (String) sourcePropValue);
        }

        //noinspection unchecked
        if (targetPropClass.isAssignableFrom(sourcePropValue.getClass())) {
            return (Enum<?>) sourcePropValue;
        }

        //if (sourcePropValue instanceof Number) -- 数字转Enum?

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in translate [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
