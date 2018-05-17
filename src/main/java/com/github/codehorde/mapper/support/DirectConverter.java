package com.github.codehorde.mapper.support;

import net.sf.cglib.core.Converter;

/**
 * 不做任何转换，但不同类型的属性可能会报错
 */
public class DirectConverter implements Converter {

    @Override
    public Object convert(Object sourcePropValue, Class target, Object context) {
        return sourcePropValue;
    }
}