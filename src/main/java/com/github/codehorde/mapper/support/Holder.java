package com.github.codehorde.mapper.support;

/**
 * Created by baomingfeng at 2018-04-28 16:04:14
 */
public class Holder<T> {

    private volatile T value;

    public Holder() {
    }

    public Holder(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "value=" + value +
                '}';
    }
}