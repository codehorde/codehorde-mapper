package com.github.codehorde.mapper.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BBB implements Serializable {

    private Long longPValue;
    private long LongValue;
    private Double doublePValue;
    private double DoubleValue;
    private Integer intPValue;
    private int IntegerValue;
    private Float floatPValue;
    private float FloatValue;
    private Short shortPValue;
    private short ShortValue;
    private Byte bytePValue;
    private byte ByteValue;

    private String timeUnit;

    private BigInteger stringInteger;
    private BigDecimal stringDecimal;

    public Long getLongPValue() {
        return longPValue;
    }

    public void setLongPValue(Long longPValue) {
        this.longPValue = longPValue;
    }

    public long getLongValue() {
        return LongValue;
    }

    public void setLongValue(long longValue) {
        LongValue = longValue;
    }

    public Double getDoublePValue() {
        return doublePValue;
    }

    public void setDoublePValue(Double doublePValue) {
        this.doublePValue = doublePValue;
    }

    public double getDoubleValue() {
        return DoubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        DoubleValue = doubleValue;
    }

    public Integer getIntPValue() {
        return intPValue;
    }

    public void setIntPValue(Integer intPValue) {
        this.intPValue = intPValue;
    }

    public int getIntegerValue() {
        return IntegerValue;
    }

    public void setIntegerValue(int integerValue) {
        IntegerValue = integerValue;
    }

    public Float getFloatPValue() {
        return floatPValue;
    }

    public void setFloatPValue(Float floatPValue) {
        this.floatPValue = floatPValue;
    }

    public float getFloatValue() {
        return FloatValue;
    }

    public void setFloatValue(float floatValue) {
        FloatValue = floatValue;
    }

    public Short getShortPValue() {
        return shortPValue;
    }

    public void setShortPValue(Short shortPValue) {
        this.shortPValue = shortPValue;
    }

    public short getShortValue() {
        return ShortValue;
    }

    public void setShortValue(short shortValue) {
        ShortValue = shortValue;
    }

    public Byte getBytePValue() {
        return bytePValue;
    }

    public void setBytePValue(Byte bytePValue) {
        this.bytePValue = bytePValue;
    }

    public byte getByteValue() {
        return ByteValue;
    }

    public void setByteValue(byte byteValue) {
        ByteValue = byteValue;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public BigInteger getStringInteger() {
        return stringInteger;
    }

    public void setStringInteger(BigInteger stringInteger) {
        this.stringInteger = stringInteger;
    }

    public BigDecimal getStringDecimal() {
        return stringDecimal;
    }

    public void setStringDecimal(BigDecimal stringDecimal) {
        this.stringDecimal = stringDecimal;
    }

    @Override
    public String toString() {
        return "BBB{" +
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
                ", timeUnit='" + timeUnit + '\'' +
                ", stringInteger=" + stringInteger +
                ", stringDecimal=" + stringDecimal +
                ", longValue=" + getLongValue() +
                ", doubleValue=" + getDoubleValue() +
                ", integerValue=" + getIntegerValue() +
                ", floatValue=" + getFloatValue() +
                ", shortValue=" + getShortValue() +
                ", byteValue=" + getByteValue() +
                '}';
    }
}