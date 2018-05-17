package com.github.codehorde.mapper.translate;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by baomingfeng at 2018-04-28 12:57:39
 */
public class StringTranslator implements PropertyTranslator<String> {

    @Override
    public String translate(Object sourcePropValue, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return String.valueOf(sourcePropValue);
        }

        if (sourcePropValue instanceof Enum) {
            return ((Enum) sourcePropValue).name();
        }

        return sourcePropValue.toString();

        /*
        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in translate [" + sourcePropValue + "] to " + targetPropType.toString());
                */
    }
}
