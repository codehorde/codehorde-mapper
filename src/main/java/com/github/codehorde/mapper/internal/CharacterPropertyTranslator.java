package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;
import com.github.codehorde.mapper.support.MathUtils;

import java.lang.reflect.Type;

/**
 * Created by Bao.mingfeng at 2018-07-17 15:44:41
 */
public class CharacterPropertyTranslator implements PropertyTranslator<Character> {
    @Override
    public Class[] registryKeys() {
        return new Class[]{Character.class, char.class};
    }

    @Override
    public Character newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof Character) {
            return (Character) sourcePropValue;
        }

        if (sourcePropValue instanceof String) {
            String sourceString = (String) sourcePropValue;
            if (sourceString.length() == 1) {
                return sourceString.charAt(0);
            }
        }

        if (sourcePropValue instanceof Number) {
            return MathUtils.toCharExact(((Number) sourcePropValue).longValue());
        }

        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropValue.getClass() + ", targetPropClass: " + targetPropType);
    }

    @Override
    public Character translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue, targetType, targetPropType);
    }
}
