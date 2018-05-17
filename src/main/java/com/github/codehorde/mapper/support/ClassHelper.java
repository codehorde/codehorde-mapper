package com.github.codehorde.mapper.support;

import net.sf.cglib.reflect.FastClass;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by baomingfeng at 2018-05-02 16:00:24
 */
public final class ClassHelper {

    private static ConcurrentMap<Class<?>, Class<?>>
            PrimaryWrapTypeMap = new ConcurrentHashMap<>();

    static {
        PrimaryWrapTypeMap.put(long.class, Long.class);
        PrimaryWrapTypeMap.put(double.class, Double.class);
        PrimaryWrapTypeMap.put(int.class, Integer.class);
        PrimaryWrapTypeMap.put(float.class, Float.class);
        PrimaryWrapTypeMap.put(short.class, Short.class);
        PrimaryWrapTypeMap.put(char.class, Character.class);
        PrimaryWrapTypeMap.put(boolean.class, Boolean.class);
        PrimaryWrapTypeMap.put(byte.class, Byte.class);
        PrimaryWrapTypeMap.put(void.class, Void.class);
    }

    private static final CopyOnWriteArraySet<Type>
            BasicClasses = new CopyOnWriteArraySet<>();

    static {
        BasicClasses.addAll(
                Arrays.asList(Long.class, Double.class, Integer.class, Short.class,
                        Character.class, Boolean.class, Void.class,
                        String.class, BigDecimal.class, BigInteger.class)
        );
        BasicClasses.addAll(PrimaryWrapTypeMap.keySet());
        BasicClasses.addAll(PrimaryWrapTypeMap.values());
    }

    public static boolean isBasicClass(Type type) {
        return BasicClasses.contains(type);
    }

    /**
     * 返回某个属性的方法的泛型参数
     * <p>
     * 属性方法只有一个参数，如方法public void setSkus(List<ItemSkuDo> skus)
     */
    public static ParameterizedType getMethodParameterType(
            Type targetType, String methodName, Class<?> parameterClass) {
        if (targetType instanceof ParameterizedType) {
            return (ParameterizedType) targetType;
        } else if (targetType instanceof Class) {
            return getMethodParameterType((Class<?>) targetType, methodName, parameterClass);
        }
        throw new IllegalArgumentException("unsupported targetType: " + targetType);
    }

    /*
        <targetClass, <methodName, <parameterClass, ParameterizedType>>>
    */
    private static ConcurrentMap<Class<?>, ConcurrentMap<String, ConcurrentMap<Class<?>, Holder<ParameterizedType>>>>
            MethodParameterTypeCache = new ConcurrentHashMap<>();

    public static ParameterizedType getMethodParameterType(
            Class<?> targetClass, String methodName, Class<?> parameterClass) {
        ConcurrentMap<String, ConcurrentMap<Class<?>, Holder<ParameterizedType>>>
                classMethodTypeMap = MethodParameterTypeCache.get(targetClass);
        if (classMethodTypeMap == null) {
            classMethodTypeMap = new ConcurrentHashMap<>();
            MethodParameterTypeCache.putIfAbsent(targetClass, classMethodTypeMap);
            classMethodTypeMap = MethodParameterTypeCache.get(targetClass);
        }

        ConcurrentMap<Class<?>, Holder<ParameterizedType>>
                methodTypeMap = classMethodTypeMap.get(methodName);
        if (methodTypeMap == null) {
            methodTypeMap = new ConcurrentHashMap<>();
            classMethodTypeMap.putIfAbsent(methodName, methodTypeMap);
            methodTypeMap = classMethodTypeMap.get(methodName);
        }

        Holder<ParameterizedType> typeHolder = methodTypeMap.get(parameterClass);
        if (typeHolder == null) {
            ParameterizedType parameterizedType = null;
            Method method = findMethod(targetClass, methodName, parameterClass);
            if (method != null) {
                Type[] parameterTypes = method.getGenericParameterTypes();
                Type type = parameterTypes[0];
                if (type instanceof ParameterizedType) {
                    parameterizedType = (ParameterizedType) type;
                }
            }

            typeHolder = new Holder<>(parameterizedType);
            methodTypeMap.putIfAbsent(parameterClass, typeHolder);//cache
        }

        return typeHolder.get();
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
    private static final ConcurrentMap<Class<?>, Method[]> declaredMethodsCache =
            new ConcurrentHashMap<>(256);

    private static Method[] getDeclaredMethods(Class<?> clazz) {
        Method[] result = declaredMethodsCache.get(clazz);
        if (result == null) {
            result = clazz.getDeclaredMethods();
            declaredMethodsCache.putIfAbsent(clazz, result);
            result = declaredMethodsCache.get(clazz);
        }
        return result;
    }

    public static Type getCollectionItemType(Type fieldType) {
        if (fieldType instanceof ParameterizedType) {
            return ((ParameterizedType) fieldType).getActualTypeArguments()[0];
        }

        return null;
    }

    public static Class<?> getWrapClass(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType itemType = (ParameterizedType) type;
            return (Class<?>) itemType.getRawType();//List<X<Y>>, Set<X<Y>>
        }
        /*
        else if (type instanceof WildcardType) {//List<X extends Y>, List<X super Y> ???
            return null;
        }
        */
        return null;
    }

    /**
     * 留意这里返回的对象不是反射调用构造方法创建出来的（Cglib FastClass）
     */
    public static <T> T instantiate(Class<?> clazz) {
        Boolean support = supportInstantiateCache.get(clazz);
        if (support == null) {
            support = isInstantiatable(clazz);
            supportInstantiateCache.putIfAbsent(clazz, support);
            support = supportInstantiateCache.get(clazz);
        }

        if (support) {
            FastClass fastClass = getFastClass(clazz);

            try {
                //noinspection unchecked
                return (T) fastClass.newInstance();
            } catch (InvocationTargetException ex) {
                throw new UnsupportedOperationException(
                        "create class [" + clazz + "] instance error! ", ex);
            }
        } else {
            if (List.class.isAssignableFrom(clazz)) {
                //noinspection unchecked
                return (T) new ArrayList();
            } else if (Set.class.isAssignableFrom(clazz)) {
                //noinspection unchecked
                return (T) new HashSet();
            } else if (Map.class.isAssignableFrom(clazz)) {
                //noinspection unchecked
                return (T) new HashMap();
            }
        }

        throw new UnsupportedOperationException(
                "create class [" + clazz + "] instance error! ");
    }

    private static final ConcurrentMap<Class<?>, Boolean> supportInstantiateCache =
            new ConcurrentHashMap<>(256);

    private static boolean isInstantiatable(Class<?> clazz) {
        if (!Modifier.isPublic(clazz.getModifiers())
                || Modifier.isAbstract(clazz.getModifiers())) {
            return false;
        }
        for (Constructor<?> constructor : clazz.getConstructors()) {
            // In Java 7-, use getParameterTypes and check the length of the array returned
            if (constructor.getParameterTypes().length == 0) {
                return true;
            }
        }
        return false;
    }

    private static final ConcurrentMap<Class<?>, FastClass>
            FastClassCache = new ConcurrentHashMap<>();

    //生成类过程比较耗费资源，使用synchronized加锁处理
    private final static Object CreateClassLock = new Object();

    public static FastClass getFastClass(Class<?> clazz) {
        FastClass result = FastClassCache.get(clazz);

        if (result == null) {
            synchronized (CreateClassLock) {
                result = FastClassCache.get(clazz);
                if (result == null) {
                    result = FastClass.create(clazz);
                    FastClassCache.put(clazz, result);
                }
            }
        }

        return result;
    }

    private ClassHelper() {
    }
}