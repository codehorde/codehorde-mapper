package com.github.codehorde.mapper.translate;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class ByteTranslator implements PropertyTranslator<Byte> {

    @Override
    public Byte translate(Object sourcePropValue, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return Byte.parseByte((String) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return ((Number) sourcePropValue).byteValue();
        }

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in translate [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
