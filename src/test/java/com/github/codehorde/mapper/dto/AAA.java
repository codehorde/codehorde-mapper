package com.github.codehorde.mapper.dto;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class AAA implements Serializable {

    private long longPValue;
    private Long LongValue;
    private double doublePValue;
    private Double DoubleValue;
    private int intPValue;
    private Integer IntegerValue;
    private float floatPValue;
    private Float FloatValue;
    private short shortPValue;
    private Short ShortValue;
    private byte bytePValue;
    private Byte ByteValue;

    private TimeUnit timeUnit;

    private String stringInteger;
    private String stringDecimal;

    public long getLongPValue() {
        return longPValue;
    }

    public void setLongPValue(long longPValue) {
        this.longPValue = longPValue;
    }

    public Long getLongValue() {
        return LongValue;
    }

    public void setLongValue(Long longValue) {
        LongValue = longValue;
    }

    public double getDoublePValue() {
        return doublePValue;
    }

    public void setDoublePValue(double doublePValue) {
        this.doublePValue = doublePValue;
    }

    public Double getDoubleValue() {
        return DoubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        DoubleValue = doubleValue;
    }

    public int getIntPValue() {
        return intPValue;
    }

    public void setIntPValue(int intPValue) {
        this.intPValue = intPValue;
    }

    public Integer getIntegerValue() {
        return IntegerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        IntegerValue = integerValue;
    }

    public float getFloatPValue() {
        return floatPValue;
    }

    public void setFloatPValue(float floatPValue) {
        this.floatPValue = floatPValue;
    }

    public Float getFloatValue() {
        return FloatValue;
    }

    public void setFloatValue(Float floatValue) {
        FloatValue = floatValue;
    }

    public short getShortPValue() {
        return shortPValue;
    }

    public void setShortPValue(short shortPValue) {
        this.shortPValue = shortPValue;
    }

    public Short getShortValue() {
        return ShortValue;
    }

    public void setShortValue(Short shortValue) {
        ShortValue = shortValue;
    }

    public byte getBytePValue() {
        return bytePValue;
    }

    public void setBytePValue(byte bytePValue) {
        this.bytePValue = bytePValue;
    }

    public Byte getByteValue() {
        return ByteValue;
    }

    public void setByteValue(Byte byteValue) {
        ByteValue = byteValue;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getStringInteger() {
        return stringInteger;
    }

    public void setStringInteger(String stringInteger) {
        this.stringInteger = stringInteger;
    }

    public String getStringDecimal() {
        return stringDecimal;
    }

    public void setStringDecimal(String stringDecimal) {
        this.stringDecimal = stringDecimal;
    }

    @Override
    public String toString() {
        return "AAA{" +
                "longPValue=" + longPValue +
                ", LongValue=" + LongValue +
                ", doublePValue=" + doublePValue +
                ", DoubleValue=" + DoubleValue +
                ", intPValue=" + intPValue +
                ", IntegerValue=" + IntegerValue +
                ", floatPValue=" + floatPValue +
                ", FloatValue=" + FloatValue +
                ", shortPValue=" + shortPValue +
                ", ShortValue=" + ShortValue +
                ", bytePValue=" + bytePValue +
                ", ByteValue=" + ByteValue +
                ", timeUnit=" + timeUnit +
                ", stringInteger='" + stringInteger + '\'' +
                ", stringDecimal='" + stringDecimal + '\'' +
                ", longValue=" + getLongValue() +
                ", doubleValue=" + getDoubleValue() +
                ", integerValue=" + getIntegerValue() +
                ", floatValue=" + getFloatValue() +
                ", shortValue=" + getShortValue() +
                ", byteValue=" + getByteValue() +
                '}';
    }
}