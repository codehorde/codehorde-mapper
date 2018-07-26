package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.BeanMapper;
import com.github.codehorde.mapper.reflection.ClassUtils;
import com.github.codehorde.mapper.reflection.TypeUtils;
import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bao.Mingfeng at 2018-05-02 13:54:09
 */
public class MapPropertyTranslator implements PropertyTranslator<Map<?, ?>> {

    private final static Class DefaultMapClass = HashMap.class;

    @Override
    public Class[] registryKeys() {
        return new Class[]{Map.class};
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Map<?, ?> newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        Class<?> sourcePropClass = sourcePropValue.getClass();
        if (ClassUtils.isInstantiatable(sourcePropClass)) {
            return ClassUtils.instantiate(sourcePropClass);
        }
        Class<?> targetPropClass = TypeUtils.getRawType(targetPropType);
        if (ClassUtils.isInstantiatable(targetPropClass)) {
            return ClassUtils.instantiate(targetPropClass);
        }
        return ClassUtils.instantiate(DefaultMapClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<?, ?> translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof Map) {
            Class<?> targetPropClass = TypeUtils.getRawType(targetPropType);
            Map retMap = newInstance(sourcePropValue, targetType, targetPropType);

            Map<?, ?> sourceMap = (Map<?, ?>) sourcePropValue;

            Type keyType = null;
            Type valueType = null;

            if (targetPropType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) targetPropType;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                if (typeArguments.length > 0) {
                    keyType = TypeUtils.resolve(targetPropType, targetPropClass, typeArguments[0]);
                }
                if (typeArguments.length > 1) {
                    valueType = TypeUtils.resolve(targetPropType, targetPropClass, typeArguments[1]);
                }
            }

            //Type[] keyAndValueTypes = TypeUtils.getMapKeyAndValueTypes(targetPropType, targetPropClass);

            for (Map.Entry<?, ?> entry : sourceMap.entrySet()) {

                Object key = entry.getKey();
                if (keyType == null) {
                    keyType = key == null ? null : key.getClass();
                }
                Object targetKey = BeanMapper.deepMapBy(key, keyType);
                Object value = entry.getValue();
                if (valueType == null) {
                    valueType = value == null ? null : value.getClass();
                }

                Object targetValue = BeanMapper.deepMapBy(value, valueType);

                retMap.put(targetKey, targetValue);
            }
            return retMap;
        }

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in internal [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
