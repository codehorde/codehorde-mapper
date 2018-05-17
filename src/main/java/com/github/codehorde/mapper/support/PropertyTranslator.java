package com.github.codehorde.mapper.support;

import java.lang.reflect.Type;

/**
 * Created by baomingfeng at 2018-04-28 12:51:16
 */
public interface PropertyTranslator<T> {

    /**
     * 属性转换器
     *
     * @param sourcePropValue 复制的源对象属性值
     * @param targetPropType  复制的目标对象属性类型，Class或者泛化类型，如 List&lt;Integer&gt;
     * @return 属性转换的结果
     */
    T translate(Object sourcePropValue, Type targetPropType);
}