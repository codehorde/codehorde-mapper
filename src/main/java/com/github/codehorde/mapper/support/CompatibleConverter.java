package com.github.codehorde.mapper.support;

import net.sf.cglib.core.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <pre>
 *     补充了一部分自动处理的方式
 *          1：目标对象同名属性类型为String，源对象属性转换
 * </pre>
 * <p>
 * Created by Bao.Mingfeng at 2018-05-02 16:00:24
 */
public class CompatibleConverter implements Converter {

    private final Type targetType;

    public CompatibleConverter(Type targetType) {
        this.targetType = targetType;
    }

    @Override
    public Object convert(Object sourcePropValue, Class targetPropClass, Object context) {
        if (sourcePropValue == null) {
            return null;
        }

        PropertyTranslator propertyTranslator = TranslatorRegistry.findPropertyTranslator(targetPropClass);
        if (propertyTranslator != null) {
            ParameterizedType methodParameterType = ClassHelper
                    .getMethodParameterType(targetType, (String) context, targetPropClass);
            if (methodParameterType == null) {
                return propertyTranslator.translate(sourcePropValue, targetPropClass);
            } else {
                return propertyTranslator.translate(sourcePropValue, methodParameterType);
            }
        }

        return null;
    }


}