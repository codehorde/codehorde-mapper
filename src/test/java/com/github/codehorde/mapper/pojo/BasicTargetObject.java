package com.github.codehorde.mapper.pojo;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class BasicTargetObject implements Serializable {

    private Long longPrimitiveValue;
    private long longWrapperValue;
    private Double doublePrimitiveValue;
    private double doubleWrapperValue;
    private Integer integerPrimitiveValue;
    private int integerWrapperValue;
    private Float floatPrimitiveValue;
    private float floatWrapperValue;
    private Short shortPrimitiveValue;
    private short shortWrapperValue;
    private Byte bytePrimitiveValue;
    private byte byteWrapperValue;
    private Boolean booleanPrimitiveValue;
    private boolean booleanWrapperValue;
    private Character characterPrimitiveValue;
    private char characterWrapperValue;

    private TimeUnit timeUnit;

    private String string;

    private Object stringObject;

    private Object originObject;

    private Integer longIntegerValue;

    private Character intCharacterValue;

    private Character longCharacterValue;

    public Long getLongPrimitiveValue() {
        return longPrimitiveValue;
    }

    public void setLongPrimitiveValue(Long longPrimitiveValue) {
        this.longPrimitiveValue = longPrimitiveValue;
    }

    public long getLongWrapperValue() {
        return longWrapperValue;
    }

    public void setLongWrapperValue(long longWrapperValue) {
        this.longWrapperValue = longWrapperValue;
    }

    public Double getDoublePrimitiveValue() {
        return doublePrimitiveValue;
    }

    public void setDoublePrimitiveValue(Double doublePrimitiveValue) {
        this.doublePrimitiveValue = doublePrimitiveValue;
    }

    public double getDoubleWrapperValue() {
        return doubleWrapperValue;
    }

    public void setDoubleWrapperValue(double doubleWrapperValue) {
        this.doubleWrapperValue = doubleWrapperValue;
    }

    public Integer getIntegerPrimitiveValue() {
        return integerPrimitiveValue;
    }

    public void setIntegerPrimitiveValue(Integer integerPrimitiveValue) {
        this.integerPrimitiveValue = integerPrimitiveValue;
    }

    public int getIntegerWrapperValue() {
        return integerWrapperValue;
    }

    public void setIntegerWrapperValue(int integerWrapperValue) {
        this.integerWrapperValue = integerWrapperValue;
    }

    public Float getFloatPrimitiveValue() {
        return floatPrimitiveValue;
    }

    public void setFloatPrimitiveValue(Float floatPrimitiveValue) {
        this.floatPrimitiveValue = floatPrimitiveValue;
    }

    public float getFloatWrapperValue() {
        return floatWrapperValue;
    }

    public void setFloatWrapperValue(float floatWrapperValue) {
        this.floatWrapperValue = floatWrapperValue;
    }

    public Short getShortPrimitiveValue() {
        return shortPrimitiveValue;
    }

    public void setShortPrimitiveValue(Short shortPrimitiveValue) {
        this.shortPrimitiveValue = shortPrimitiveValue;
    }

    public short getShortWrapperValue() {
        return shortWrapperValue;
    }

    public void setShortWrapperValue(short shortWrapperValue) {
        this.shortWrapperValue = shortWrapperValue;
    }

    public Byte getBytePrimitiveValue() {
        return bytePrimitiveValue;
    }

    public void setBytePrimitiveValue(Byte bytePrimitiveValue) {
        this.bytePrimitiveValue = bytePrimitiveValue;
    }

    public byte getByteWrapperValue() {
        return byteWrapperValue;
    }

    public void setByteWrapperValue(byte byteWrapperValue) {
        this.byteWrapperValue = byteWrapperValue;
    }

    public Boolean getBooleanPrimitiveValue() {
        return booleanPrimitiveValue;
    }

    public void setBooleanPrimitiveValue(Boolean booleanPrimitiveValue) {
        this.booleanPrimitiveValue = booleanPrimitiveValue;
    }

    public boolean isBooleanWrapperValue() {
        return booleanWrapperValue;
    }

    public void setBooleanWrapperValue(boolean booleanWrapperValue) {
        this.booleanWrapperValue = booleanWrapperValue;
    }

    public Character getCharacterPrimitiveValue() {
        return characterPrimitiveValue;
    }

    public void setCharacterPrimitiveValue(Character characterPrimitiveValue) {
        this.characterPrimitiveValue = characterPrimitiveValue;
    }

    public char getCharacterWrapperValue() {
        return characterWrapperValue;
    }

    public void setCharacterWrapperValue(char characterWrapperValue) {
        this.characterWrapperValue = characterWrapperValue;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Object getStringObject() {
        return stringObject;
    }

    public void setStringObject(Object stringObject) {
        this.stringObject = stringObject;
    }

    public Object getOriginObject() {
        return originObject;
    }

    public void setOriginObject(Object originObject) {
        this.originObject = originObject;
    }

    public Integer getLongIntegerValue() {
        return longIntegerValue;
    }

    public void setLongIntegerValue(Integer longIntegerValue) {
        this.longIntegerValue = longIntegerValue;
    }

    public Character getIntCharacterValue() {
        return intCharacterValue;
    }

    public void setIntCharacterValue(Character intCharacterValue) {
        this.intCharacterValue = intCharacterValue;
    }

    public Character getLongCharacterValue() {
        return longCharacterValue;
    }

    public void setLongCharacterValue(Character longCharacterValue) {
        this.longCharacterValue = longCharacterValue;
    }

    @Override
    public String toString() {
        return "BasicTargetObject{" +
                "longPrimitiveValue=" + longPrimitiveValue +
                ", longWrapperValue=" + longWrapperValue +
                ", doublePrimitiveValue=" + doublePrimitiveValue +
                ", doubleWrapperValue=" + doubleWrapperValue +
                ", integerPrimitiveValue=" + integerPrimitiveValue +
                ", integerWrapperValue=" + integerWrapperValue +
                ", floatPrimitiveValue=" + floatPrimitiveValue +
                ", floatWrapperValue=" + floatWrapperValue +
                ", shortPrimitiveValue=" + shortPrimitiveValue +
                ", shortWrapperValue=" + shortWrapperValue +
                ", bytePrimitiveValue=" + bytePrimitiveValue +
                ", byteWrapperValue=" + byteWrapperValue +
                ", booleanPrimitiveValue=" + booleanPrimitiveValue +
                ", booleanWrapperValue=" + booleanWrapperValue +
                ", characterPrimitiveValue=" + characterPrimitiveValue +
                ", characterWrapperValue=" + characterWrapperValue +
                ", timeUnit=" + timeUnit +
                ", string='" + string + '\'' +
                ", stringObject=" + stringObject +
                ", originObject=" + originObject +
                ", longIntegerValue=" + longIntegerValue +
                ", intCharacterValue=" + intCharacterValue +
                ", longCharacterValue=" + longCharacterValue +
                '}';
    }
}