package com.github.codehorde.mapper.support;

import com.github.codehorde.mapper.translate.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Bao.Mingfeng at 2018-05-02 16:03:35
 */
public final class TranslatorRegistry {
    /*
        TargetPropertyClass --> PropertyConverter
    */
    private static ConcurrentMap<Class<?>, PropertyTranslator>
            PropertyTranslatorMap = new ConcurrentHashMap<>();

    static {
        PropertyTranslatorMap.put(String.class, new StringTranslator());
        PropertyTranslatorMap.put(BigInteger.class, new BigIntegerTranslator());
        PropertyTranslatorMap.put(BigDecimal.class, new BigDecimalTranslator());
        PropertyTranslatorMap.put(byte.class, new ByteTranslator());
        PropertyTranslatorMap.put(Byte.class, new ByteTranslator());
        PropertyTranslatorMap.put(double.class, new DoubleTranslator());
        PropertyTranslatorMap.put(Double.class, new DoubleTranslator());
        PropertyTranslatorMap.put(float.class, new FloatTranslator());
        PropertyTranslatorMap.put(Float.class, new FloatTranslator());
        PropertyTranslatorMap.put(int.class, new IntegerTranslator());
        PropertyTranslatorMap.put(Integer.class, new IntegerTranslator());
        PropertyTranslatorMap.put(long.class, new LongTranslator());
        PropertyTranslatorMap.put(Long.class, new LongTranslator());
        PropertyTranslatorMap.put(short.class, new ShortTranslator());
        PropertyTranslatorMap.put(Short.class, new ShortTranslator());
        PropertyTranslatorMap.put(boolean.class, new BooleanTranslator());
        PropertyTranslatorMap.put(Boolean.class, new BooleanTranslator());
        //
        PropertyTranslatorMap.put(Enum.class, new EnumTranslator());
        //
        PropertyTranslatorMap.put(Array.class, new ArrayTranslator());
        PropertyTranslatorMap.put(List.class, new ListTranslator());
        PropertyTranslatorMap.put(Set.class, new SetTranslator());
        PropertyTranslatorMap.put(Map.class, new MapTranslator());
        //
        PropertyTranslatorMap.put(Object.class, new BeanTranslator());
    }

    public static PropertyTranslator mapPropertyTranslator(Class<?> targetPropClass) {
        return PropertyTranslatorMap.get(targetPropClass);
    }

    /*
        targetPropClass --> Holder<PropertyTranslator>
    */
    private static ConcurrentMap<Class<?>, Holder<PropertyTranslator>>
            CachePropertyTranslatorMap = new ConcurrentHashMap<>();

    /**
     * 根据目标的属性类型获取对象的转换器
     */
    public static PropertyTranslator findPropertyTranslator(Class targetPropClass) {
        if (targetPropClass == null) {
            return TranslatorRegistry.mapPropertyTranslator(Object.class);
        }

        Holder<PropertyTranslator> holder = CachePropertyTranslatorMap.get(targetPropClass);

        if (holder != null) {
            return holder.get();
        }

        PropertyTranslator translator = null;

        if (List.class.isAssignableFrom(targetPropClass)) {
            translator = TranslatorRegistry.mapPropertyTranslator(List.class);
        } else if (Set.class.isAssignableFrom(targetPropClass)) {
            translator = TranslatorRegistry.mapPropertyTranslator(Set.class);
        } else if (Map.class.isAssignableFrom(targetPropClass)) {
            translator = TranslatorRegistry.mapPropertyTranslator(Map.class);
        } else if (targetPropClass.isArray()) {
            translator = TranslatorRegistry.mapPropertyTranslator(Array.class);
        } else {
            Class<?> searchType = targetPropClass;
            while (!Object.class.equals(searchType) && searchType != null) {
                translator = TranslatorRegistry.mapPropertyTranslator(searchType);
                if (translator != null) {
                    break;
                }
                searchType = searchType.getSuperclass();
            }
        }

        if (translator == null) {
            translator = TranslatorRegistry.mapPropertyTranslator(Object.class);
        }

        holder = new Holder<>(translator);
        CachePropertyTranslatorMap.putIfAbsent(targetPropClass, holder);

        return translator;
    }

    private TranslatorRegistry() {
    }
}
