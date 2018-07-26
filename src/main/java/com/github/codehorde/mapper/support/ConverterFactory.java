package com.github.codehorde.mapper.support;

import com.github.codehorde.mapper.reflection.ClassUtils;
import net.sf.cglib.core.Converter;

import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Bao.mingfeng at 2018-07-18 16:43:27
 */
@SuppressWarnings("unchecked")
public final class ConverterFactory {

    private final static DirectConverter directConverter = new DirectConverter();

    public static DirectConverter directConverter() {
        return directConverter;
    }

    private final static ConcurrentMap<Type, SmartConverter>
            SmartConverterCache = new ConcurrentHashMap<>();

    public static SmartConverter smartConverter(Type targetType) {
        SmartConverter smartConverter = SmartConverterCache.get(targetType);
        if (smartConverter == null) {
            SmartConverterCache.putIfAbsent(targetType, new SmartConverter(targetType));
        }
        return SmartConverterCache.get(targetType);
    }

    /**
     * 不做任何转换，不兼容类型的属性转换会抛出ClassCastException
     */
    public static class DirectConverter implements Converter {

        @Override
        public Object convert(Object sourcePropValue, Class target, Object context) {
            return sourcePropValue;
        }
    }

    /**
     * <pre>
     *     补充了一部分自动处理的方式
     *          1：目标对象同名属性类型为String，源对象属性转换
     * </pre>
     */
    public static class SmartConverter implements Converter {

        private final Type targetType;

        public SmartConverter(Type targetType) {
            this.targetType = targetType;
        }

        @Override
        public Object convert(Object sourcePropValue, Class targetPropClass, Object context) {
            if (sourcePropValue == null) {
                return null;
            }

            PropertyTranslator propertyTranslator = Registry.findPropertyTranslator(targetPropClass);
            if (propertyTranslator != null) {
                Type type = ClassUtils.getMethodParameterType(targetType, (String) context, targetPropClass);
                return propertyTranslator.translate(sourcePropValue, targetType, type);
            }

            return null;
        }


    }
}
