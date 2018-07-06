package com.github.codehorde.mapper;

import com.github.codehorde.mapper.dto.AAA;
import com.github.codehorde.mapper.dto.BBB;
import com.github.codehorde.mapper.dto.CCC;
import net.sf.cglib.core.DebuggingClassWriter;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bao.Mingfeng at 2018-04-28 16:17:15
 */
public class BeanMapperUtilsTest {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/Bao.Mingfeng/Downloads/cglib");

        test1();
        //test2();
    }

    public static void test1() {
        AAA aaa = new AAA();
        aaa.setLongValue(1L);
        aaa.setLongPValue(2L);
        aaa.setDoubleValue(3.0);
        aaa.setDoublePValue(4.0);
        aaa.setIntegerValue(5);
        aaa.setIntPValue(6);
        aaa.setFloatValue(7.0f);
        aaa.setFloatPValue(8.0f);
        aaa.setShortValue((short) 9);
        aaa.setShortPValue((short) 10);
        aaa.setByteValue((byte) 11);
        aaa.setBytePValue((byte) 12);
        aaa.setStringDecimal("23463466.28");
        aaa.setStringInteger("79200000");
        aaa.setTimeUnit(TimeUnit.HOURS);

        BBB bbb1 = new BBB();
        BeanMapper.simpleCopy(aaa, bbb1);
        System.out.println(bbb1);

        BBB bbb2 = new BBB();
        BeanMapper.deepCopyFrom(aaa, BBB.class);
        System.out.println(bbb2);

        BBB bbb3 = new BBB();
        BeanMapper.deepCopyFrom(aaa, BBB.class);
        System.out.println(bbb3);

        BBB bbb4 = new BBB();
        BeanMapper.deepCopyFrom(aaa, BBB.class);
        System.out.println(bbb4);

        //StringDecimal会报错
        BBB bbbx = new BBB();
        BeanMapper.directCopy(aaa, bbbx);
        System.out.println(bbbx);
    }

    public static void test2() {
        AAA aaa = new AAA();
        aaa.setLongValue(1L);
        aaa.setLongPValue(2L);
        aaa.setDoubleValue(3.0);
        aaa.setDoublePValue(4.0);
        aaa.setIntegerValue(5);
        aaa.setIntPValue(6);
        aaa.setFloatValue(7.0f);
        aaa.setFloatPValue(8.0f);
        aaa.setShortValue((short) 9);
        aaa.setShortPValue((short) 10);
        aaa.setByteValue((byte) 11);
        aaa.setBytePValue((byte) 12);
        aaa.setStringDecimal("23463466.28");
        aaa.setStringInteger("79200000");
        aaa.setTimeUnit(TimeUnit.HOURS);

        BBB bbb = new BBB();
        BeanMapper.deepCopyFrom(aaa, BBB.class);
        System.out.println(bbb);
    }

    public static void test3() {
        CCC ccc = new CCC();
        ccc.setLongValue("1");
        ccc.setLongPValue("2");
        ccc.setDoubleValue("3.0");
        ccc.setDoublePValue("4.0");
        ccc.setIntegerValue("5");
        ccc.setIntPValue("6");
        ccc.setFloatValue("7.0");
        ccc.setFloatPValue("8.0");
        ccc.setShortValue("9");
        ccc.setShortPValue("10");
        ccc.setByteValue("11");
        ccc.setBytePValue("12");
        ccc.setStringDecimal("23463466.28");
        ccc.setStringInteger("79200000");
        ccc.setTimeUnit(TimeUnit.HOURS);

        BBB bbb = new BBB();
        BeanMapper.deepCopyFrom(ccc, BBB.class);
        System.out.println(bbb);
    }
}
