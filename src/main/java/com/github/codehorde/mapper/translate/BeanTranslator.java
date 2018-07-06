package com.github.codehorde.mapper.translate;

import com.github.codehorde.mapper.BeanMapper;
import com.github.codehorde.mapper.support.ClassHelper;
import com.github.codehorde.mapper.support.PropertyTranslator;

import java.lang.reflect.Type;

/**
 * 普通对象互相复制
 * <p>
 * Created by Bao.Mingfeng at 2018-05-02 16:59:01
 */
public class BeanTranslator implements PropertyTranslator<Object> {

    @Override
    public Object translate(Object sourcePropValue, Type targetPropType) {
        Class<?> targetClass = ClassHelper.getWrapClass(targetPropType);
        
        if (targetClass == null) {
            return null;
        }

        Object target = ClassHelper.instantiate(targetClass);
        BeanMapper.deepCopy(sourcePropValue, target, targetPropType);
        return target;
    }
}
