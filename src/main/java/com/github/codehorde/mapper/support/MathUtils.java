package com.github.codehorde.mapper.support;

/**
 * Created by Bao.mingfeng at 2018-07-17 19:24:22
 */
public final class MathUtils {

    /**
     * Returns the value of the {@code long} argument;
     * throwing an exception if the value overflows an {@code int}.
     *
     * @param value the long value
     * @return the argument as an int
     * @throws ArithmeticException if the {@code argument} overflows an int
     * @since 1.8
     */
    public static int toIntExact(long value) {
        if ((int) value != value) {
            throw new ArithmeticException("integer overflow: " + value);
        }
        return (int) value;
    }

    public static short toShortExact(long value) {
        if ((short) value != value) {
            throw new ArithmeticException("short overflow: " + value);
        }
        return (short) value;
    }

    public static char toCharExact(long value) {
        if ((char) value != value) {
            throw new ArithmeticException("char overflow: " + value);
        }
        return (char) value;
    }

    public static byte toByteExact(long value) {
        if ((byte) value != value) {
            throw new ArithmeticException("byte overflow: " + value);
        }
        return (byte) value;
    }

    public static float toFloatExact(double value) {
        if ((float) value != value) {
            throw new ArithmeticException("float overflow: " + value);
        }
        return (float) value;
    }

    private MathUtils() {
    }
}
