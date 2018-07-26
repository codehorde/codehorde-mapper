package com.github.codehorde.mapper.internal;

import com.github.codehorde.mapper.BeanMapper;
import com.github.codehorde.mapper.reflection.ClassUtils;
import com.github.codehorde.mapper.reflection.TypeUtils;
import com.github.codehorde.mapper.support.PropertyTranslator;
import com.github.codehorde.mapper.support.Registry;

import java.lang.reflect.Type;

/**
 * 普通POJO对象的复制
 * <p>
 * Created by Bao.Mingfeng at 2018-05-02 16:59:01
 */
public class BeanPropertyTranslator implements PropertyTranslator<Object> {

    @Override
    public Class[] registryKeys() {
        return new Class[]{Registry.PojoRegistryClass};
    }

    /**
     * <pre>
     * 1:targetPropClass可以实例化
     *  return: 使用targetPropClass实例化对象
     * 2:targetPropClass不能实例化 & targetPropClass是sourcePropClass的父类 & sourcePropClass可以实例化
     *  return: 使用sourcePropClass实例化对象
     * 3:抛出例外
     * </pre>
     */
    @Override
    public Object newInstance(Object sourcePropValue, Type targetType, Type targetPropType) {
        Class<?> targetPropClass = TypeUtils.getRawType(targetPropType);
        if (ClassUtils.isInstantiatable(targetPropClass)) {
            return ClassUtils.instantiate(targetPropClass);
        }
        Class<?> sourcePropClass = sourcePropValue.getClass();
        if (targetPropClass.isAssignableFrom(sourcePropClass)
                && ClassUtils.isInstantiatable(sourcePropClass)) {
            return ClassUtils.instantiate(sourcePropClass);
        }

        throw new UnsupportedOperationException(getClass().getSimpleName()
                + ": cannot create instance, sourcePropClass: "
                + sourcePropClass + ", targetPropClass: " + targetPropType);
    }

    @Override
    public Object translate(Object sourcePropValue, Type targetType, Type targetPropType) {
        if (targetPropType == Object.class) {//目标属性为Object.class，取sourcePropClass类型实例化
            Class<?> sourcePropClass = sourcePropValue.getClass();
            PropertyTranslator<?> propertyTranslator = Registry.findPropertyTranslator(sourcePropClass);
            return propertyTranslator.newInstance(sourcePropValue, targetType, sourcePropClass);
        }

        Object target = newInstance(sourcePropValue, targetType, targetPropType);

        BeanMapper.deepMap(sourcePropValue, target, targetPropType);

        return target;
    }

}
