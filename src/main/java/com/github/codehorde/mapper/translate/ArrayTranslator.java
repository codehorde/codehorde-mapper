package com.github.codehorde.mapper.translate;

import com.github.codehorde.mapper.BeanMapper;
import com.github.codehorde.mapper.support.ClassHelper;
import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-05-02 13:54:09
 */
@SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
public class ArrayTranslator implements PropertyTranslator<Object> {

    @Override
    public Object translate(Object sourcePropValue, Type targetPropType) {
        Class<?> sourcePropClass = sourcePropValue.getClass();

        if (sourcePropClass.isArray()) {
            Class targetPropClass = (Class) targetPropType;
            Class componentType = targetPropClass.getComponentType();
            int len = Array.getLength(sourcePropValue);
            //TODO 对于基础类型如byte[]数组是否有必要创建一个数组用于复制？
            Object retArray = Array.newInstance(componentType, len);
            if (ClassHelper.isBasicClass(componentType)) {
                System.arraycopy(sourcePropValue, 0, retArray, 0, len);
            } else {
                for (int index = 0; index < len; index++) {
                    Object source = Array.get(sourcePropValue, index);
                    Object target = BeanMapper.deepCopyFrom(source, componentType);
                    Array.set(retArray, index, target);
                }
            }
            return retArray;
        }

        /*
            目前只支持数组转数组 A[] --> B[]，原则上可支持List, Set --> B[]
        */

        throw new IllegalArgumentException(getClass().getSimpleName()
                + ": Error in translate [" + sourcePropValue + "] to " + targetPropType.toString());
    }
}
