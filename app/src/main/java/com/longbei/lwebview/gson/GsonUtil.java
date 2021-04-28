package com.longbei.lwebview.gson;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuml
 * @explain
 * @time 2018/2/1 10:08
 */

public class GsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                    .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                    .registerTypeAdapter(int.class, new IntegerTypeAdapter())
                    .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                    .registerTypeAdapter(double.class, new DoubleTypeAdapter())
                    .registerTypeAdapter(Long.class, new LongTypeAdapter())
                    .registerTypeAdapter(long.class, new LongTypeAdapter())
                    .registerTypeAdapter(Float.class, new FloatTypeAdapter())
                    .registerTypeAdapter(float.class, new FloatTypeAdapter())
                    .create();
        }
    }


    private GsonUtil() {
    }


    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    public static String gsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }


//    public static <T> RootResponseBean<T> jsonToBean(String jsonStr, Class<T> clazz) {
//        Type type = new ParameterizedTypeImpl(RootResponseBean.class, new Class[]{clazz});
//        return gson.fromJson(jsonStr, type);
//    }

    public static <T> RootResponseBean<List<T>> jsonToListBean(String jsonStr, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(RootResponseBean.class, new Type[]{listType});
        return gson.fromJson(jsonStr, type);
    }

//    /**
//     * 转成list
//     * 泛型在编译期类型被擦除导致报错
//     *
//     * @param gsonString
//     * @param cls
//     * @return
//     */
//    public static <T> List<T> gsonToList(String gsonString, Class<T> cls) {
//        List<T> list = null;
//        if (gson != null) {
//            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
//            }.getType());
//        }
//        return list;
//    }


    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
//        Gson gson = new Gson();
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

//

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

//
//    /**
//     * 转成map的
//     *
//     * @param gsonString
//     * @return
//     */
//    public static <T> Map<String, T> GsonToMaps(String gsonString) {
//        Map<String, T> map = null;
//        if (gson != null) {
//            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
//            }.getType());
//        }
//        return map;
//    }

    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
//    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
//        T t = null;
//        if (gson != null && !TextUtils.isEmpty(gsonString)) {
//            t = gson.fromJson(gsonString, cls);
//        }
//        return t;
//    }
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null && !TextUtils.isEmpty(gsonString)) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

//    public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
//        List<T> list = null;
//        if (gson != null) {
//            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
//            }.getType());
//        }
//
//        return list;
//    }


    public static Map<String, Object> toMap(String jsonStr) {
        Map<String, Object> map = null;
        if (gson != null) {
            map = gson.fromJson(jsonStr, new TypeToken<HashMap<String, Object>>() {
            }.getType());

        }
        return map;
    }


}
