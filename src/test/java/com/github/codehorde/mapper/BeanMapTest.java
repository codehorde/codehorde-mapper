package com.github.codehorde.mapper;

import com.github.codehorde.mapper.pojo.BasicSourceObject;
import net.sf.cglib.core.DebuggingClassWriter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bao.mingfeng at 2018-07-26 20:26:40
 */
public class BeanMapTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String debugFolder = System.getProperty("user.home") + "/Downloads/cglib";
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, debugFolder);

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

        Map<String, Object> objectMap = BeanMapper.describe(source);
        System.out.println(objectMap);

        objectMap = BeanMapper.describe(new BasicSourceObject());
        System.out.println(objectMap);

        objectMap = BeanMapper.describe(new Object());
        System.out.println(objectMap);

        objectMap = BeanMapper.describe(new Thread());
        System.out.println(objectMap);

        Map sourceMap = Collections.singletonMap("stringObject", "XXXXXX");
        BasicSourceObject target = BeanMapper.populate(sourceMap, BasicSourceObject.class);
        System.out.println(target);

        target = new BasicSourceObject();
        BeanMapper.populate(sourceMap, target);
        System.out.println(target);
    }
}
