package com.github.codehorde.mapper.support;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Bao.Mingfeng at 2018-05-09 13:37:18
 */
public class TypeRef<T> {

    private final Type type;

    protected TypeRef() {
        Type superClass = getClass().getGenericSuperclass();
        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}
