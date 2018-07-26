package com.github.codehorde.mapper.pojo;

import java.io.Serializable;

public class BasicSourceObject implements Serializable {

    private long longPrimitiveValue;
    private Long longWrapperValue;
    private double doublePrimitiveValue;
    private Double doubleWrapperValue;
    private int integerPrimitiveValue;
    private Integer integerWrapperValue;
    private float floatPrimitiveValue;
    private Float floatWrapperValue;
    private short shortPrimitiveValue;
    private Short shortWrapperValue;
    private byte bytePrimitiveValue;
    private Byte byteWrapperValue;
    private boolean booleanPrimitiveValue;
    private Boolean booleanWrapperValue;
    private char characterPrimitiveValue;
    private Character characterWrapperValue;

    private String timeUnit;

    private String string;

    private String stringObject;

    private Object originObject;

    private long longIntegerValue;

    private int intCharacterValue;

    private long longCharacterValue;

    public long getLongPrimitiveValue() {
        return longPrimitiveValue;
    }

    public void setLongPrimitiveValue(long longPrimitiveValue) {
        this.longPrimitiveValue = longPrimitiveValue;
    }

    public Long getLongWrapperValue() {
        return longWrapperValue;
    }

    public void setLongWrapperValue(Long longWrapperValue) {
        this.longWrapperValue = longWrapperValue;
    }

    public double getDoublePrimitiveValue() {
        return doublePrimitiveValue;
    }

    public void setDoublePrimitiveValue(double doublePrimitiveValue) {
        this.doublePrimitiveValue = doublePrimitiveValue;
    }

    public Double getDoubleWrapperValue() {
        return doubleWrapperValue;
    }

    public void setDoubleWrapperValue(Double doubleWrapperValue) {
        this.doubleWrapperValue = doubleWrapperValue;
    }

    public int getIntegerPrimitiveValue() {
        return integerPrimitiveValue;
    }

    public void setIntegerPrimitiveValue(int integerPrimitiveValue) {
        this.integerPrimitiveValue = integerPrimitiveValue;
    }

    public Integer getIntegerWrapperValue() {
        return integerWrapperValue;
    }

    public void setIntegerWrapperValue(Integer integerWrapperValue) {
        this.integerWrapperValue = integerWrapperValue;
    }

    public float getFloatPrimitiveValue() {
        return floatPrimitiveValue;
    }

    public void setFloatPrimitiveValue(float floatPrimitiveValue) {
        this.floatPrimitiveValue = floatPrimitiveValue;
    }

    public Float getFloatWrapperValue() {
        return floatWrapperValue;
    }

    public void setFloatWrapperValue(Float floatWrapperValue) {
        this.floatWrapperValue = floatWrapperValue;
    }

    public short getShortPrimitiveValue() {
        return shortPrimitiveValue;
    }

    public void setShortPrimitiveValue(short shortPrimitiveValue) {
        this.shortPrimitiveValue = shortPrimitiveValue;
    }

    public Short getShortWrapperValue() {
        return shortWrapperValue;
    }

    public void setShortWrapperValue(Short shortWrapperValue) {
        this.shortWrapperValue = shortWrapperValue;
    }

    public byte getBytePrimitiveValue() {
        return bytePrimitiveValue;
    }

    public void setBytePrimitiveValue(byte bytePrimitiveValue) {
        this.bytePrimitiveValue = bytePrimitiveValue;
    }

    public Byte getByteWrapperValue() {
        return byteWrapperValue;
    }

    public void setByteWrapperValue(Byte byteWrapperValue) {
        this.byteWrapperValue = byteWrapperValue;
    }

    public boolean isBooleanPrimitiveValue() {
        return booleanPrimitiveValue;
    }

    public void setBooleanPrimitiveValue(boolean booleanPrimitiveValue) {
        this.booleanPrimitiveValue = booleanPrimitiveValue;
    }

    public Boolean getBooleanWrapperValue() {
        return booleanWrapperValue;
    }

    public void setBooleanWrapperValue(Boolean booleanWrapperValue) {
        this.booleanWrapperValue = booleanWrapperValue;
    }

    public char getCharacterPrimitiveValue() {
        return characterPrimitiveValue;
    }

    public void setCharacterPrimitiveValue(char characterPrimitiveValue) {
        this.characterPrimitiveValue = characterPrimitiveValue;
    }

    public Character getCharacterWrapperValue() {
        return characterWrapperValue;
    }

    public void setCharacterWrapperValue(Character characterWrapperValue) {
        this.characterWrapperValue = characterWrapperValue;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getStringObject() {
        return stringObject;
    }

    public void setStringObject(String stringObject) {
        this.stringObject = stringObject;
    }

    public Object getOriginObject() {
        return originObject;
    }

    public void setOriginObject(Object originObject) {
        this.originObject = originObject;
    }

    public long getLongIntegerValue() {
        return longIntegerValue;
    }

    public void setLongIntegerValue(long longIntegerValue) {
        this.longIntegerValue = longIntegerValue;
    }

    public int getIntCharacterValue() {
        return intCharacterValue;
    }

    public void setIntCharacterValue(int intCharacterValue) {
        this.intCharacterValue = intCharacterValue;
    }

    public long getLongCharacterValue() {
        return longCharacterValue;
    }

    public void setLongCharacterValue(long longCharacterValue) {
        this.longCharacterValue = longCharacterValue;
    }

    @Override
    public String toString() {
        return "BasicSourceObject{" +
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
                ", timeUnit='" + timeUnit + '\'' +
                ", string='" + string + '\'' +
                ", stringObject='" + stringObject + '\'' +
                ", originObject=" + originObject +
                ", longIntegerValue=" + longIntegerValue +
                ", intCharacterValue=" + intCharacterValue +
                ", longCharacterValue=" + longCharacterValue +
                '}';
    }
}