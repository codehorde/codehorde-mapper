package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.BeanMapper;
import com.github.codehorde.mapper.reflection.ClassUtils;
import com.github.codehorde.mapper.reflection.TypeUtils;
import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-05-02 13:54:09
 */
public class ArrayPropertyTranslator implements PropertyTranslator<Object> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{Array.class};
    }


    /**
     * TODO 对于基础类型如byte[]数组是否有必要创建一个数组用于复制？
     */
    @Override
    public Object newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        Type componentType = TypeUtils.getArrayComponentType(targetPropType);
        Class<?> componentClass = TypeUtils.getRawType(componentType);
        int len = Array.getLength(sourcePropValue);
        return Array.newInstance(componentClass, len);
    }

    @Override
    public Object translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        Class<?> sourcePropClass = sourcePropValue.getClass();

        if (sourcePropClass.isArray()) {
            Type componentType = TypeUtils.getArrayComponentType(targetPropType);
            if (ClassUtils.isBasicClass(componentType)) {//TODO 类型byte[]这种数组就不开辟内存数组复制了
                //System.arraycopy(sourcePropValue, 0, retArray, 0, len);
                return sourcePropValue;
            }

            int len = Array.getLength(sourcePropValue);
            Object retArray = newInstance(sourcePropValue, targetType, targetPropType);

            for (int index = 0; index < len; index++) {
                Object source = Array.get(sourcePropValue, index);
                Object target = BeanMapper.deepMapBy(source, componentType);
                Array.set(retArray, index, target);
            }

            return retArray;
        }

        /*
            目前只支持数组转数组 A[] --> B[]，可拓展支持List<?>, Set<?> --> B[]
        */

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in internal [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
