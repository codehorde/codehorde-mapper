package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class FloatPropertyTranslator implements PropertyTranslator<Float> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{Float.class, float.class};
    }

    @Override
    public Float newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return Float.parseFloat((String) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            Number number = (Number) sourcePropValue;
            double value = number.doubleValue();
            if (value > Float.MAX_VALUE || value < Float.MIN_VALUE) {
                throw new ArithmeticException("float overflow: " + value);
            }
            return number.floatValue();
        }
        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropValue.getClass() + ", targetPropClass: " + targetPropType);
    }

    @Override
    public Float translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue, targetType, targetPropType);
    }
}
