package com.github.codehorde.mapper.support;

import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-04-28 12:51:16
 */
public interface PropertyTranslator<T> {

    /**
     * 用于注册缓存的标识Class
     */
    Class[] registryKeys();

    /**
     * 由源对象和目标类创建实例
     */
    T newInstance(Object sourcePropValue, Type targetType, Type targetPropType);

    /**
     * 属性转换器
     *
     * @param sourcePropValue 复制的源对象属性值（始终为对象类型）
     * @param targetType      复制的目标类型（ParameterizedType或者Class）
     * @param targetPropType  复制的目标对象属性类型，Class或者泛化类型，如 List&lt;Integer&gt, T[] (T为泛型参数);
     * @return 属性转换的结果
     */
    T translate(Object sourcePropValue, Type targetType, Type targetPropType);
}