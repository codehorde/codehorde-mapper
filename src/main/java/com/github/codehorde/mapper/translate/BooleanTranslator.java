package com.github.codehorde.mapper.translate;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class BooleanTranslator implements PropertyTranslator<Boolean> {

    @Override
    public Boolean translate(Object sourcePropValue, Type targetPropType) {
        if (sourcePropValue instanceof Boolean) {
            return ((Boolean) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return ((Number) sourcePropValue).intValue() == 1;
        }

        if (sourcePropValue instanceof String) {
            return Boolean.valueOf((String) sourcePropValue);
        }

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in translate [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
