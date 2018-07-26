package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class BigDecimalPropertyTranslator implements PropertyTranslator<BigDecimal> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{BigDecimal.class};
    }

    @Override
    public BigDecimal newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return new BigDecimal((String) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return new BigDecimal(String.valueOf(sourcePropValue));
        }

        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropValue.getClass() + ", targetPropClass: " + targetPropType);
    }

    @Override
    public BigDecimal translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        return newInstance(sourcePropValue,  targetType,  targetPropType);
    }
}
