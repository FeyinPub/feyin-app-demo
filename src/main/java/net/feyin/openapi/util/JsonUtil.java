package net.feyin.openapi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static final int TYPE_FASTJSON = 0;
    public static final int TYPE_GSON = 1;

    public JsonUtil() {
    }

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static String toJson(Object obj, SerializerFeature... features) {
        return JSON.toJSONString(obj, features);
    }

    public static String toJson(Object obj, boolean format) {
        return JSON.toJSONString(obj, format);
    }

    public static String toJson(Object obj, final String[] fields, final boolean ignore, SerializerFeature... features) {
        if(fields != null && fields.length >= 1) {
            if(features == null) {
                features = new SerializerFeature[]{SerializerFeature.QuoteFieldNames};
            }

            return JSON.toJSONString(obj, (PropertyFilter) (object, name, value) -> {
                for (String field : fields) {
                    if (name.equals(field)) {
                        return !ignore;
                    }
                }

                return ignore;
            }, features);
        } else {
            return toJson(obj);
        }
    }

    public static <E> E parse(String json, String path) {
        String[] keys = path.split(",");
        JSONObject obj = JSON.parseObject(json);

        for(int i = 0; i < keys.length - 1; ++i) {
            obj = obj.getJSONObject(keys[i]);
        }

        return (E)obj.get(keys[keys.length - 1]);
    }

    public static <T> T parse(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> T parse(String json, String path, Class<T> clazz) {
        String[] keys = path.split(",");
        JSONObject obj = JSON.parseObject(json);

        for(int inner = 0; inner < keys.length - 1; ++inner) {
            obj = obj.getJSONObject(keys[inner]);
        }

        String var6 = obj.getString(keys[keys.length - 1]);
        return parse(var6, clazz);
    }

    public static <T> List<T> parseArray(Object obj, String[] fields, boolean ignore, Class<T> clazz, SerializerFeature... features) {
        String json = toJson(obj, fields, ignore, features);
        return parseArray(json, clazz);
    }

    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    public static <T> List<T> parseArray(String json, String path, Class<T> clazz) {
        String[] keys = path.split(",");
        JSONObject obj = JSON.parseObject(json);

        for(int inner = 0; inner < keys.length - 1; ++inner) {
            obj = obj.getJSONObject(keys[inner]);
        }

        String s = obj.getString(keys[keys.length - 1]);
        return parseArray(s, clazz);
    }

    public static String correctJson(String invalidJson) {
        return invalidJson.replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}");
    }

    public static String formatJson(String json) {
        Map map = (Map)JSON.parse(json);
        return JSON.toJSONString(map, true);
    }

    public static String getSubJson(String json, String path) {
        String[] keys = path.split(",");
        JSONObject obj = JSON.parseObject(json);

        for(int i = 0; i < keys.length - 1; ++i) {
            obj = obj.getJSONObject(keys[i]);
        }

        return obj != null?obj.getString(keys[keys.length - 1]):null;
    }
}
