package com.github.codehorde.mapper.reflection;

import com.github.codehorde.mapper.support.Holder;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.reflect.FastClass;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Bao.Mingfeng at 2018-05-02 16:00:24
 */
@SuppressWarnings("unchecked")
public final class ClassUtils {

    //这里指不可变类型或者领域值对象类型等
    private final static Set<Type> BasicTypes = new CopyOnWriteArraySet<>();

    static {
        BasicTypes.add(boolean.class);
        BasicTypes.add(Boolean.class);
        BasicTypes.add(char.class);
        BasicTypes.add(Character.class);
        BasicTypes.add(byte.class);
        BasicTypes.add(Byte.class);
        BasicTypes.add(short.class);
        BasicTypes.add(Short.class);
        BasicTypes.add(int.class);
        BasicTypes.add(Integer.class);
        BasicTypes.add(long.class);
        BasicTypes.add(Long.class);
        BasicTypes.add(float.class);
        BasicTypes.add(Float.class);
        BasicTypes.add(double.class);
        BasicTypes.add(Double.class);
        BasicTypes.add(void.class);
        BasicTypes.add(Void.class);
        BasicTypes.add(BigInteger.class);
        BasicTypes.add(BigDecimal.class);

        BasicTypes.add(String.class);

        //严格来说java.util.Date不是不可变类型，但这里问题不大
        BasicTypes.add(java.util.Date.class);
        BasicTypes.add(java.sql.Date.class);
        BasicTypes.add(java.sql.Time.class);
        BasicTypes.add(java.sql.Timestamp.class);
    }

    public static boolean isBasicClass(Type type) {
        return BasicTypes.contains(type);
    }

    /*
        fast access
        <Type, <String("methodName(parameterClass)"), Type>>>
    */
    private static ConcurrentMap<Type, ConcurrentMap<String, Holder<Type>>>
            MethodParameterTypeCache = new ConcurrentHashMap<>();

    public static Type getMethodParameterType(Type targetType, String methodName, Class<?> parameterClass) {
        ConcurrentMap<String, Holder<Type>> holderMap = MethodParameterTypeCache.get(targetType);
        if (holderMap == null) {
            MethodParameterTypeCache.putIfAbsent(targetType, new ConcurrentHashMap<String, Holder<Type>>());
            holderMap = MethodParameterTypeCache.get(targetType);
        }

        String methodKey = methodName + "(" + parameterClass.getName() + ")";
        Holder<Type> typeHolder = holderMap.get(methodKey);
        if (typeHolder != null) {
            return typeHolder.get();
        }

        Type retType;
        if (targetType instanceof Class) {
            retType = getClassMethodType((Class<?>) targetType, methodName, parameterClass);
        } else if (targetType instanceof ParameterizedType) {
            retType = getParameterizedTypeMethodType((ParameterizedType) targetType, methodName, parameterClass);
        } else {
            //GenericArrayType or WildcardType is not illegal
            throw new IllegalArgumentException("unsupported targetType: " + targetType);
        }

        holderMap.putIfAbsent(methodKey, new Holder<>(retType));

        return retType;
    }

    /**
     * <pre>
     * 获取参数化类型setter属性方法中的泛型参数
     *
     * eg.
     *  PageResult&lt;T&gt;，其中T为泛型，存在一个setList(List&lt;T&gt; list)方法
     *  PageResult&lt;XxxDto&gt;中获取setList方法的参数类型应该返回List&lt;XxxDto&gt;
     * </pre>
     */
    public static Type getParameterizedTypeMethodType(
            ParameterizedType clazzType, String methodName, Class<?> parameterClass) {
        Class<?> clazz = (Class<?>) clazzType.getRawType();
        ParameterizedType argsType = null;
        Method method = ClassUtils.findMethod(clazz, methodName, parameterClass);
        if (method != null) {
            Type[] parameterTypes = method.getGenericParameterTypes();
            Type _argsType = parameterTypes[0];
            return TypeUtils.resolve(clazzType, clazz, _argsType);
        }
        return null;
    }

    /**
     * <pre>
     * 返回某个属性的方法的泛型参数（属性方法只有一个参数）
     *
     * eg：
     *  -- 方法public void setItemSkuDo(ItemSkuDo itemSkuDo)返回Class: ItemSkuDo
     *  -- 方法public void setSkus(List&lt;ItemSkuDo&gt; skus)返回ParameterizedType: List&lt;ItemSkuDo&gt;
     *  -- 方法public void setItemSkus(T[] itemSkuDo)返回GenericArrayType: T[]，需要Types.resolve()
     *  ...
     * </pre>
     */
    public static Type getClassMethodType(Class<?> targetClass, String methodName, Class<?> parameterClass) {
        Method method = findMethod(targetClass, methodName, parameterClass);
        if (method == null) {
            throw new IllegalStateException("cannot find method from targetClass: "
                    + targetClass + ", methodName: " + methodName + ", parameterClass: " + parameterClass);
        }
        Type[] parameterTypes = method.getGenericParameterTypes();
        return parameterTypes[0];
    }

    public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
        Class<?> searchType = clazz;
        while (searchType != null) {
            Method[] methods = (searchType.isInterface() ? searchType.getMethods() : getDeclaredMethods(searchType));
            for (Method method : methods) {
                if (name.equals(method.getName()) &&
                        (paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes()))) {
                    return method;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    /**
     * Cache for {@link Class#getDeclaredMethods()}, allowing for fast resolution.
     */
    private static final ConcurrentMap<Class<?>, Method[]> declaredMethodsCache = new ConcurrentHashMap<>(256);

    private static Method[] getDeclaredMethods(Class<?> clazz) {
        Method[] result = declaredMethodsCache.get(clazz);
        if (result == null) {
            result = clazz.getDeclaredMethods();
            declaredMethodsCache.putIfAbsent(clazz, result);
        }
        return declaredMethodsCache.get(clazz);
    }

    private static final ConcurrentMap<Class<?>, Boolean>
            supportInstantiateCache = new ConcurrentHashMap<>(256);

    /**
     * 判断某个类型是否能够实例化
     */
    public static boolean isInstantiatable(Class<?> clazz) {
        Boolean support = supportInstantiateCache.get(clazz);
        if (support == null) {
            support = false;
            if (Modifier.isPublic(clazz.getModifiers())
                    && !Modifier.isAbstract(clazz.getModifiers())) {
                for (Constructor<?> constructor : clazz.getConstructors()) {
                    // In Java 7-, use getParameterTypes and check the length of the array returned
                    if (constructor.getParameterTypes().length == 0) {
                        support = true;
                    }
                }
            }
            supportInstantiateCache.putIfAbsent(clazz, support);
        }

        return support;
    }

    /**
     * Cglib FastClass创建实例
     */
    public static <T> T instantiate(Class<?> clazz) {
        boolean support = isInstantiatable(clazz);

        if (!support) {
            throw new UnsupportedOperationException(
                    "cannot instantiate class [" + clazz + "]! ");
        }

        FastClass fastClass = getFastClass(clazz);
        try {
            return (T) fastClass.newInstance();
        } catch (InvocationTargetException ex) {
            throw new UnsupportedOperationException(
                    "instantiate class [" + clazz + "] occur an unexpected error! ", ex);
        }
    }

    private static final ConcurrentMap<Class<?>, FastClass> FastClassCache = new ConcurrentHashMap<>();

    //生成类过程比较耗费资源，使用synchronized加锁处理
    private final static Object GenerateClassLock = new Object();

    public static FastClass getFastClass(Class<?> clazz) {
        FastClass result = FastClassCache.get(clazz);

        if (result == null) {
            synchronized (GenerateClassLock) {
                result = FastClassCache.get(clazz);
                if (result == null) {
                    result = FastClass.create(clazz);
                    FastClassCache.put(clazz, result);
                }
            }
        }

        return result;
    }


    /*
        String(SourceClass/TargetClass/useConverter) --> BeanCopier
    */
    private static ConcurrentMap<String, BeanCopier> beanCopiers = new ConcurrentHashMap<>();

    public static BeanCopier findCopier(Class<?> sourceClass, Class<?> targetClass) {
        return findCopier(sourceClass, targetClass, false);
    }

    public static BeanCopier findCopier(Class<?> sourceClass, Class<?> targetClass, boolean useConverter) {
        String cacheKey = sourceClass.getName()
                + "/" + targetClass.getCanonicalName()
                + "/" + String.valueOf(useConverter);

        BeanCopier copier = beanCopiers.get(cacheKey);
        if (copier == null) {
            synchronized (GenerateClassLock) {
                copier = beanCopiers.get(cacheKey);
                if (copier == null) {
                    copier = BeanCopier.create(sourceClass, targetClass, useConverter);
                    beanCopiers.put(cacheKey, copier);
                }
            }
        }

        return copier;
    }

    private ClassUtils() {
    }
}