package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.BeanMapper;
import com.github.codehorde.mapper.reflection.ClassUtils;
import com.github.codehorde.mapper.reflection.TypeUtils;
import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bao.Mingfeng at 2018-05-02 13:54:09
 */
@SuppressWarnings("unchecked")
public class ListPropertyTranslator implements PropertyTranslator<List<?>> {

    private final static Class DefaultListClass = ArrayList.class;

    @Override
    public Class[] registryKeys() {
        return new Class[]{List.class};
    }

    /**
     * 优先顺序：源属性类型创建 --> 目标属性类型创建 --> DefaultListClass
     */
    @SuppressWarnings("Duplicates")
    @Override
    public List<?> newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        Class<?> sourcePropClass = sourcePropValue.getClass();
        if (ClassUtils.isInstantiatable(sourcePropClass)) {
            return ClassUtils.instantiate(sourcePropClass);
        }
        Class<?> targetPropClass = TypeUtils.getRawType(targetPropType);
        if (ClassUtils.isInstantiatable(targetPropClass)) {
            return ClassUtils.instantiate(targetPropClass);
        }
        return ClassUtils.instantiate(DefaultListClass);
    }

    @Override
    public List<?> translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof List) {
            Class<?> targetPropClass = TypeUtils.getRawType(targetPropType);
            List retList = newInstance(sourcePropValue, targetType, targetPropType);
            List<?> sourceList = (List<?>) sourcePropValue;
            Type componentType = TypeUtils.getCollectionElementType(targetPropType, targetPropClass);

            for (Object source : sourceList) {
                Object target = BeanMapper.deepMapBy(source, componentType);
                retList.add(target);
            }
            return retList;
        }

        /*
            可拓展支持Array, Set --> List<?>
        */

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in internal [" + sourcePropValue + "] to " + targetPropType);
    }
}
