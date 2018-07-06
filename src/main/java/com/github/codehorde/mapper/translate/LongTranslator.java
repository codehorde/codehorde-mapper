package com.github.codehorde.mapper.translate;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class LongTranslator implements PropertyTranslator<Long> {

    @Override
    public Long translate(Object sourcePropValue, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return Long.parseLong((String) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return ((Number) sourcePropValue).longValue();
        }

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in translate [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
