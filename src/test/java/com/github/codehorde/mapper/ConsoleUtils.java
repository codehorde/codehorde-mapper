package com.github.codehorde.mapper;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by Bao.mingfeng at 2018-07-18 15:03:49
 */
public final class ConsoleUtils {

    public static void prettyPrint(Object obj) {
        System.err.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
        System.err.println(JSONObject.toJSONString(obj, SerializerFeature.PrettyFormat));
        System.err.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
    }

    private ConsoleUtils() {
    }
}
