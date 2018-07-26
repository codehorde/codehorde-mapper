package com.github.codehorde.mapper;

import com.github.codehorde.mapper.reflection.ClassUtils;
import com.github.codehorde.mapper.reflection.TypeUtils;
import com.github.codehorde.mapper.support.ConverterFactory;
import com.github.codehorde.mapper.support.PropertyTranslator;
import com.github.codehorde.mapper.support.Registry;
import net.sf.cglib.beans.BeanCopier;

import java.lang.reflect.Type;

/**
 * 基于对象属性名称相同的对象属性复制 note: 属性为getter, setter方法所定义，非类的成员变量
 * <p>
 * Created by Bao.Mingfeng at 2018-04-28 11:04:59
 */
public final class BeanMapper {

    /**
     * Cglib BeanCopier基础的功能，源属性对象和目标属性对象名称相同但类型不同，目标属性处理为null
     *
     * @param sourceObject 源对象
     * @param targetObject 目标对象
     */
    public static void simpleMap(Object sourceObject, Object targetObject) {
        if (sourceObject == null || targetObject == null) {
            return;
        }
        BeanCopier copier = ClassUtils.findCopier(sourceObject.getClass(), targetObject.getClass());
        copier.copy(sourceObject, targetObject, null);
    }

    /**
     * @param sourceObject 源对象
     * @param targetClass  目标对象Class
     * @see BeanMapper#simpleMap(java.lang.Object, java.lang.Object)
     */
    public static <T> T simpleMapBy(Object sourceObject, Class<T> targetClass) {
        if (sourceObject == null || targetClass == null) {
            return null;
        }
        BeanCopier copier = ClassUtils.findCopier(sourceObject.getClass(), targetClass);
        T target = createInstance(sourceObject, targetClass);
        copier.copy(sourceObject, target, null);
        return target;
    }

    /**
     * 源属性对象和目标属性对象属性名称相同但类型不同，目标属性使用强制转换的结果，转换可能会出现异常错误（ClassCastException）
     *
     * @param sourceObject 源对象
     * @param targetObject 目标对象
     */
    public static void directMap(Object sourceObject, Object targetObject) {
        if (sourceObject == null || targetObject == null) {
            return;
        }
        BeanCopier copier = ClassUtils.findCopier(sourceObject.getClass(), targetObject.getClass(), true);
        copier.copy(sourceObject, targetObject, ConverterFactory.directConverter());
    }

    /**
     * @param sourceObject 源对象
     * @param targetClass  目标对象Class
     * @see BeanMapper#directMapBy(java.lang.Object, java.lang.Class)
     */
    public static <T> T directMapBy(Object sourceObject, Class<T> targetClass) {
        if (sourceObject == null || targetClass == null) {
            return null;
        }
        BeanCopier copier = ClassUtils.findCopier(sourceObject.getClass(), targetClass, true);
        T target = createInstance(sourceObject, targetClass);
        copier.copy(sourceObject, target, ConverterFactory.directConverter());
        return target;
    }

    /**
     * 如果源属性和目标属性名称相同但类型不同，尝试从支持的转换器中转换，参见PropertyTranslator
     *
     * @param sourceObject 源对象
     * @param targetObject 目标对象
     */
    public static void deepMap(Object sourceObject, Object targetObject) {
        if (sourceObject == null || targetObject == null) {
            return;
        }
        BeanCopier copier = ClassUtils.findCopier(sourceObject.getClass(), targetObject.getClass(), true);
        copier.copy(sourceObject, targetObject, ConverterFactory.smartConverter(targetObject.getClass()));
    }

    /**
     * @param sourceObject 源对象
     * @param targetClass  目标对象Class
     */
    public static <T> T deepMapBy(Object sourceObject, Class<T> targetClass) {
        if (sourceObject == null || targetClass == null) {
            return null;
        }
        T target = createInstance(sourceObject, targetClass);
        BeanCopier copier = ClassUtils.findCopier(sourceObject.getClass(), target.getClass(), true);
        copier.copy(sourceObject, target, ConverterFactory.smartConverter(target.getClass()));
        return target;
    }

    /**
     * <pre>
     * 目标带有泛型参数时，为了正确转换需要带上该类型参数
     *  eg.
     *      sourceObject: PageResult&lt;ItemDto&gt; dtoResult
     *      targetObject: PageResult&lt;ItemVo&gt; dtoResult
     *      targetType: ParameterizedType --&gt; PageResult&lt;ItemVo&gt;
     *      BeanMapper.deepMap(Object sourceObject, Object targetObject, Type targetType)
     *
     * </pre>
     *
     * @param sourceObject 源对象
     * @param targetObject 目标对象
     * @param targetType   目标对象Type
     */
    public static void deepMap(Object sourceObject, Object targetObject, Type targetType) {
        if (sourceObject == null || targetObject == null) {
            return;
        }

        if (targetType == null) {
            targetType = targetObject.getClass();
        }

        BeanCopier copier = ClassUtils.findCopier(sourceObject.getClass(), targetObject.getClass(), true);
        copier.copy(sourceObject, targetObject, ConverterFactory.smartConverter(targetType));
    }

    /**
     * @param sourceObject  源对象
     * @param targetTypeRef 目标对象Type
     * @see BeanMapper#deepMap(java.lang.Object, java.lang.Object, java.lang.reflect.Type)
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepMapBy(Object sourceObject, TypeRef<T> targetTypeRef) {
        if (sourceObject == null || targetTypeRef == null) {
            return null;
        }
        Type type = targetTypeRef.getType();
        return (T) deepMapBy(sourceObject, type);
    }

    /**
     * @param sourceObject 源对象
     * @param targetType   目标对象Type
     * @see BeanMapper#deepMap(java.lang.Object, java.lang.Object, java.lang.reflect.Type)
     */
    public static Object deepMapBy(Object sourceObject, Type targetType) {
        if (sourceObject == null || targetType == null) {
            return null;
        } else if (ClassUtils.isBasicClass(targetType)) {
            return sourceObject;
        } else {
            Class<?> targetClass = TypeUtils.getRawType(targetType);
            PropertyTranslator translator = Registry.findPropertyTranslator(targetClass);
            return translator.translate(sourceObject, targetType, targetType);
        }
    }

    /**
     * 由源对象和目标Class创建targetObject
     *
     * @param sourceObject 该参数是当源对象为数组时，需要知道源对象数组的长度
     * @param targetClass  目标对象Class
     */
    @SuppressWarnings("unchecked")
    public static <T> T createInstance(Object sourceObject, Class<T> targetClass) {
        if (ClassUtils.isInstantiatable(targetClass)) {
            return ClassUtils.instantiate(targetClass);
        }
        PropertyTranslator translator = Registry.findPropertyTranslator(targetClass);
        return (T) translator.newInstance(sourceObject, targetClass, targetClass);
    }

    private BeanMapper() {
    }
}