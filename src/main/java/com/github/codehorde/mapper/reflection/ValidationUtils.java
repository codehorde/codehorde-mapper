package com.github.codehorde.mapper.reflection;

/**
 * Created by Bao.mingfeng at 2018-07-18 16:28:52
 */
class ValidationUtils {
    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static void checkArgument(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    private ValidationUtils() {
    }
}
