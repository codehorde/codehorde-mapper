package com.github.codehorde.mapper.dto;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class CCC implements Serializable {

    private String longPValue;
    private String LongValue;
    private String doublePValue;
    private String DoubleValue;
    private String intPValue;
    private String IntegerValue;
    private String floatPValue;
    private String FloatValue;
    private String shortPValue;
    private String ShortValue;
    private String bytePValue;
    private String ByteValue;

    private TimeUnit timeUnit;

    private String stringInteger;
    private String stringDecimal;

    public String getLongPValue() {
        return longPValue;
    }

    public void setLongPValue(String longPValue) {
        this.longPValue = longPValue;
    }

    public String getLongValue() {
        return LongValue;
    }

    public void setLongValue(String longValue) {
        LongValue = longValue;
    }

    public String getDoublePValue() {
        return doublePValue;
    }

    public void setDoublePValue(String doublePValue) {
        this.doublePValue = doublePValue;
    }

    public String getDoubleValue() {
        return DoubleValue;
    }

    public void setDoubleValue(String doubleValue) {
        DoubleValue = doubleValue;
    }

    public String getIntPValue() {
        return intPValue;
    }

    public void setIntPValue(String intPValue) {
        this.intPValue = intPValue;
    }

    public String getIntegerValue() {
        return IntegerValue;
    }

    public void setIntegerValue(String integerValue) {
        IntegerValue = integerValue;
    }

    public String getFloatPValue() {
        return floatPValue;
    }

    public void setFloatPValue(String floatPValue) {
        this.floatPValue = floatPValue;
    }

    public String getFloatValue() {
        return FloatValue;
    }

    public void setFloatValue(String floatValue) {
        FloatValue = floatValue;
    }

    public String getShortPValue() {
        return shortPValue;
    }

    public void setShortPValue(String shortPValue) {
        this.shortPValue = shortPValue;
    }

    public String getShortValue() {
        return ShortValue;
    }

    public void setShortValue(String shortValue) {
        ShortValue = shortValue;
    }

    public String getBytePValue() {
        return bytePValue;
    }

    public void setBytePValue(String bytePValue) {
        this.bytePValue = bytePValue;
    }

    public String getByteValue() {
        return ByteValue;
    }

    public void setByteValue(String byteValue) {
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
        return "CCC{" +
                "longPValue='" + longPValue + '\'' +
                ", LongValue='" + LongValue + '\'' +
                ", doublePValue='" + doublePValue + '\'' +
                ", DoubleValue='" + DoubleValue + '\'' +
                ", intPValue='" + intPValue + '\'' +
                ", IntegerValue='" + IntegerValue + '\'' +
                ", floatPValue='" + floatPValue + '\'' +
                ", FloatValue='" + FloatValue + '\'' +
                ", shortPValue='" + shortPValue + '\'' +
                ", ShortValue='" + ShortValue + '\'' +
                ", bytePValue='" + bytePValue + '\'' +
                ", ByteValue='" + ByteValue + '\'' +
                ", timeUnit=" + timeUnit +
                ", stringInteger='" + stringInteger + '\'' +
                ", stringDecimal='" + stringDecimal + '\'' +
                ", longValue='" + getLongValue() + '\'' +
                ", doubleValue='" + getDoubleValue() + '\'' +
                ", integerValue='" + getIntegerValue() + '\'' +
                ", floatValue='" + getFloatValue() + '\'' +
                ", shortValue='" + getShortValue() + '\'' +
                ", byteValue='" + getByteValue() + '\'' +
                '}';
    }
}