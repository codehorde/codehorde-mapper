package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;
import com.github.codehorde.mapper.support.MathUtils;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class ShortPropertyTranslator implements PropertyTranslator<Short> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{Short.class, short.class};
    }

    @Override
    public Short newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return Short.parseShort((String) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return MathUtils.toShortExact(((Number) sourcePropValue).longValue());
        }

        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropValue.getClass() + ", targetPropClass: " + targetPropType);
    }

    @Override
    public Short translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue,  targetType,  targetPropType);
    }
}
