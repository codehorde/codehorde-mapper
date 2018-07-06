package com.github.codehorde.mapper.translate;

import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:57:39
 */
public class BigDecimalTranslator implements PropertyTranslator<BigDecimal> {

    @Override
    public BigDecimal translate(Object sourcePropValue, Type targetPropType) {
        if (sourcePropValue instanceof String) {
            return new BigDecimal((String) sourcePropValue);
        }

        if (sourcePropValue instanceof Number) {
            return new BigDecimal(String.valueOf(sourcePropValue));
        }

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in translate [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
