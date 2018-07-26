package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.BeanMapper;
import com.github.codehorde.mapper.reflection.ClassUtils;
import com.github.codehorde.mapper.reflection.TypeUtils;
import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bao.Mingfeng at 2018-05-02 13:54:09
 */
@SuppressWarnings("unchecked")
public class SetPropertyTranslator implements PropertyTranslator<Set<?>> {

    private final static Class DefaultSetClass = HashSet.class;

    @Override
    public Class[] registryKeys() {
        return new Class[]{Set.class};
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Set<?> newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        Class<?> sourcePropClass = sourcePropValue.getClass();
        if (ClassUtils.isInstantiatable(sourcePropClass)) {
            return ClassUtils.instantiate(sourcePropClass);
        }
        Class<?> targetPropClass = TypeUtils.getRawType(targetPropType);
        if (ClassUtils.isInstantiatable(targetPropClass)) {
            return ClassUtils.instantiate(targetPropClass);
        }
        return ClassUtils.instantiate(DefaultSetClass);
    }

    @Override
    public Set<?> translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (sourcePropValue instanceof Set) {
            Class<?> targetPropClass = TypeUtils.getRawType(targetPropType);
            Set retSet = newInstance(sourcePropValue, targetType, targetPropType);
            Set<?> sourceSet = (Set<?>) sourcePropValue;
            Type componentType = TypeUtils.getCollectionElementType(targetPropType, targetPropClass);
            for (Object source : sourceSet) {
                Object target = BeanMapper.deepMapBy(source, componentType);
                retSet.add(target);
            }
            return retSet;
        }

        /*
            可拓展支持Array, Set --> List<B>
        */

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in internal [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
