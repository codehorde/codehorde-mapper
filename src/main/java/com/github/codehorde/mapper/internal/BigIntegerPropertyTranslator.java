package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;
import java.math.BigInteger;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class BigIntegerPropertyTranslator implements PropertyTranslator<BigInteger> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{BigInteger.class};
    }

    @Override
    public BigInteger newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return new BigInteger((String) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return BigInteger.valueOf(((Number) sourcePropValue).longValue());
        }

        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropValue.getClass() + ", targetPropClass: " + targetPropType);
    }

    @Override
    public BigInteger translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue,  targetType,  targetPropType);
    }
}
