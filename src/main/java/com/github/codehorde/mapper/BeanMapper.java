package com.github.codehorde.mapper;

import com.github.codehorde.mapper.support.*;
import net.sf.cglib.beans.BeanCopier;

import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 对象复制
 * <p>
 * Created by Bao.Mingfeng at 2018-04-28 11:04:59
 */
@SuppressWarnings("unchecked")
public final class BeanMapper {

    private BeanMapper() {
    }

    /**
     * Cglib BeanCopier基础的功能，源属性对象和目标属性对象名称相同但类型不同，目标属性处理为null
     */
    public static void simpleCopy(Object source, Object to) {
        BeanCopier copier = findCopier(source.getClass(), to.getClass());
        copier.copy(source, to, null);//Converter可有可无，BeanCopier没有重载的函数
    }

    public static <T> T simpleCopy(Object source, Class<T> targetClass) {
        BeanCopier copier = findCopier(source.getClass(), targetClass.getClass());
        T target = ClassHelper.instantiate(targetClass);
        copier.copy(source, target, null);
        return target;
    }

    /**
     * 源属性对象和目标属性对象属性名称相同但类型不同，不做任何转换，
     * 目标属性使用强制转换的结果，转换可能会出现异常错误（ClassCastException）
     */
    public static void directCopy(Object source, Object target) {
        BeanCopier copier = findCopier(source.getClass(), target.getClass(), true);
        copier.copy(source, target, new DirectConverter());
    }

    public static <T> T directCopy(Object source, Class<T> targetClass) {
        BeanCopier copier = findCopier(source.getClass(), targetClass, true);
        T target = ClassHelper.instantiate(targetClass);
        copier.copy(source, target, new DirectConverter());
        return target;
    }

    /**
     * <pre>
     * 如果源属性和目标属性名称相同但类型不同，尝试从支持的转换器中转换，参见PropertyTranslator
     * 在此过程中：
     *     1、如果目标属性类型支持转换（能根据该类型匹配PropertyTranslator），复制使用源对象属性值转换后结果，
     *     如源属性不能转换目标类型，PropertyTranslator处理过程中可能会抛出错误
     *
     *     2、如果目标的属性类型不支持的转换，复制使用源对象对应的属性值，
     *     目标对象使用强制转换的结果，可能会报转换异常错误（ClassCastException）
     * </pre>
     */
    public static void deepCopy(Object source, Object target) {
        BeanCopier copier = findCopier(source.getClass(), target.getClass(), true);
        copier.copy(source, target, new CompatibleConverter(target.getClass()));
    }

    public static void deepCopy(Object source, Object target, Type type) {
        BeanCopier copier = findCopier(source.getClass(), target.getClass(), true);
        copier.copy(source, target, new CompatibleConverter(type));
    }

    public static <T> T deepCopyFrom(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }
        T target = ClassHelper.instantiate(targetClass);
        deepCopy(source, target);
        return target;
    }

    public static <T> T deepCopyFrom(Object value, TypeRef<T> typeRef) {
        if (value == null || typeRef == null) {
            return null;
        }
        Type type = typeRef.getType();
        return (T) deepCopyFrom(value, type);
    }

    public static Object deepCopyFrom(Object value, Type type) {
        if (value == null || type == null) {
            return null;
        } else if (ClassHelper.isBasicClass(type)) {
            return value;
        } else {
            PropertyTranslator translator = TranslatorRegistry.findPropertyTranslator(ClassHelper.getWrapClass(type));
            return translator.translate(value, type);
        }
    }

    /*
        <SourceClass#TargetClass#useConverter, BeanCopier>
    */
    private static ConcurrentMap<String, BeanCopier>
            beanCopiers = new ConcurrentHashMap<>();

    public static BeanCopier findCopier(Class<?> sourceClass, Class<?> targetClass) {
        return findCopier(sourceClass, targetClass, false);
    }

    //生成类过程比较耗费资源，使用synchronized加锁处理
    private final static Object CreateClassLock = new Object();

    public static BeanCopier findCopier(Class<?> sourceClass, Class<?> targetClass, boolean useConverter) {
        String cacheKey = sourceClass.getName()
                + "/" + targetClass.getCanonicalName()
                + "/" + String.valueOf(useConverter);

        BeanCopier copier = beanCopiers.get(cacheKey);
        if (copier == null) {
            synchronized (CreateClassLock) {
                copier = beanCopiers.get(cacheKey);
                if (copier == null) {
                    copier = BeanCopier.create(sourceClass, targetClass, useConverter);
                    beanCopiers.put(cacheKey, copier);
                }
            }
        }

        return copier;
    }

}