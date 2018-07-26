package com.github.codehorde.mapper;

import com.github.codehorde.mapper.pojo.BasicSourceObject;
import com.github.codehorde.mapper.pojo.BasicTargetObject;
import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bao.mingfeng at 2018-07-20 14:34:04
 */
public class BeanMapperShowcase {

    @Before
    public void before() {
        String debugFolder = System.getProperty("user.home") + "/Downloads/cglib";
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, debugFolder);
    }

    @Test
    public void testBasic() {
        BasicSourceObject source = new BasicSourceObject();
        source.setLongPrimitiveValue(8L);
        source.setLongWrapperValue(8L);
        source.setDoublePrimitiveValue(8.0);
        source.setDoubleWrapperValue(8.0);
        source.setIntegerPrimitiveValue(4);
        source.setIntegerWrapperValue(4);
        source.setFloatPrimitiveValue(4.0F);
        source.setFloatWrapperValue(4.0F);
        source.setShortPrimitiveValue((short) 2);
        source.setShortWrapperValue((short) 2);
        source.setBytePrimitiveValue((byte) 1);
        source.setByteWrapperValue((byte) 1);
        source.setBooleanPrimitiveValue(false);
        source.setBooleanWrapperValue(false);
        source.setCharacterPrimitiveValue((char) 2);
        source.setCharacterWrapperValue((char) 2);
        source.setTimeUnit(TimeUnit.SECONDS.name());
        source.setString("String");
        source.setStringObject("String Content");
        source.setOriginObject(Arrays.asList("ListItem1", "ListItem2"));
        source.setIntCharacterValue(47);
        source.setLongCharacterValue(100);
        source.setLongIntegerValue(324346426L);
        //同一种类型对象直接复制
        BasicSourceObject targetSimple1 = new BasicSourceObject();
        BeanMapper.simpleMap(source, targetSimple1);
        System.err.println(targetSimple1);

        BasicTargetObject targetSimple2 = new BasicTargetObject();
        BeanMapper.simpleMap(source, targetSimple2);
        System.err.println(targetSimple2);

        BasicTargetObject targetSimple3 =
                BeanMapper.simpleMapBy(source, BasicTargetObject.class);
        System.err.println(targetSimple3);

        BasicSourceObject targetDirect1 = new BasicSourceObject();
        BeanMapper.directMap(source, targetDirect1);
        System.err.println(targetDirect1);

        try {
            BasicTargetObject targetDirect2 = new BasicTargetObject();
            BeanMapper.directMap(source, targetDirect2);
            System.err.println(targetDirect2);
        } catch (ClassCastException ex) {
            System.err.println("Success! (ignore the error)");
            ex.printStackTrace();
        }

        try {
            BasicTargetObject targetDirect3 = BeanMapper.directMapBy(source, BasicTargetObject.class);
            System.err.println(targetDirect3);
        } catch (ClassCastException ex) {
            System.err.println("Success! (ignore the error)");
            ex.printStackTrace();
        }

        BasicSourceObject targetMapper1 = new BasicSourceObject();
        BeanMapper.deepMap(source, targetMapper1);
        System.err.println(targetMapper1);

        BasicTargetObject targetMapper2 = BeanMapper.deepMapBy(source, BasicTargetObject.class);
        System.err.println(targetMapper2);

        BasicTargetObject targetMapper3 = new BasicTargetObject();
        BeanMapper.deepMap(source, targetMapper3);
        System.err.println(targetMapper3);
    }

    public static void test2() {
        BasicSourceObject source = new BasicSourceObject();
        source.setLongPrimitiveValue(8L);
        source.setLongWrapperValue(8L);
        source.setDoublePrimitiveValue(8.0);
        source.setDoubleWrapperValue(8.0);
        source.setIntegerPrimitiveValue(4);
        source.setIntegerWrapperValue(4);
        source.setFloatPrimitiveValue(4.0F);
        source.setFloatWrapperValue(4.0F);
        source.setShortPrimitiveValue((short) 2);
        source.setShortWrapperValue((short) 2);
        source.setBytePrimitiveValue((byte) 1);
        source.setByteWrapperValue((byte) 1);
        source.setBooleanPrimitiveValue(false);
        source.setBooleanWrapperValue(false);
        source.setCharacterPrimitiveValue((char) 2);
        source.setCharacterWrapperValue((char) 2);
        source.setTimeUnit(TimeUnit.SECONDS.name());

        BasicTargetObject target = new BasicTargetObject();
        BeanMapper.deepMap(source, BasicTargetObject.class);
        System.err.println(target);
    }

    public static void test3() {
    }
}
