package com.github.codehorde.mapper.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Bao.Mingfeng at 2018-05-02 16:03:35
 */
public final class Registry {

    private final static Log LOG = LogFactory.getLog(Registry.class);

    /**
     * 普通pojo对象转换器KeyClass
     *
     * @see com.github.codehorde.mapper.internal.BeanPropertyTranslator
     */
    public final static Class PojoRegistryClass = Object.class;

    /*
        targetPropClass --> Holder<PropertyTranslator>
    */
    private final static ConcurrentMap<Class<?>, Holder<PropertyTranslator>> PropertyTranslatorCache;

    static {
        PropertyTranslatorCache = new ConcurrentHashMap<>();
        loadInitialPropertyTranslators();
        //System.err.println("PropertyTranslator Registered: " + PropertyTranslatorCache);
    }

    private static void loadInitialPropertyTranslators() {
        //内置PropertyTranslator
        ClassLoader packageLoader = Registry.class.getClassLoader();
        registerPropertyTranslators(ServiceLoader.load(PropertyTranslator.class, packageLoader));
        //自定义可以覆盖内置PropertyTranslator
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (packageLoader != contextClassLoader) {
            registerPropertyTranslators(ServiceLoader.load(PropertyTranslator.class, contextClassLoader));
        }
    }

    private static void registerPropertyTranslators(ServiceLoader<PropertyTranslator> loadedTranslators) {
        Iterator<PropertyTranslator> iterator = loadedTranslators.iterator();

        try {
            while (iterator.hasNext()) {
                PropertyTranslator translator = iterator.next();
                Holder<PropertyTranslator> holder = new Holder<>(translator);
                Class[] registryKeys = translator.registryKeys();
                for (Class registryKey : registryKeys) {
                    PropertyTranslatorCache.put(registryKey, holder);
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            LOG.error(t.getMessage(), t);
        }
    }

    /**
     * 根据目标的属性类型获取对象的转换器
     */
    @SuppressWarnings("unchecked")
    public static <T> PropertyTranslator<T> findPropertyTranslator(Class<T> targetPropClass) {

        //fast cache
        Holder<PropertyTranslator> holder = PropertyTranslatorCache.get(targetPropClass);

        if (holder != null) {
            return holder.get();
        }

        Class registryKey;
        if (targetPropClass.isArray()) {
            registryKey = Array.class;
        } else if (targetPropClass.isEnum()) {
            registryKey = Enum.class;
        } else if (List.class.isAssignableFrom(targetPropClass)) {
            registryKey = List.class;
        } else if (Set.class.isAssignableFrom(targetPropClass)) {
            registryKey = Set.class;
        } else if (Map.class.isAssignableFrom(targetPropClass)) {
            registryKey = Map.class;
        } else {
            registryKey = PojoRegistryClass;
        }

        holder = PropertyTranslatorCache.get(registryKey);

        PropertyTranslatorCache.putIfAbsent(targetPropClass, holder);

        return holder.get();
    }

    private Registry() {
    }
}
