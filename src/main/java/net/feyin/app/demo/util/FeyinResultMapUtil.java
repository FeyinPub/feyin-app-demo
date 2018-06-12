package net.feyin.app.demo.util;

import com.google.common.collect.Maps;

import java.util.Map;

public class FeyinResultMapUtil {

    public static Map<String, Object> getSuccessMap() {
        Map<String, Object> success = Maps.newHashMap();
        success.put("code", "0");
        success.put("msg", "success");
        return success;
    }

    public static Map<String, Object> getSuccessDataMap(Object data) {
        Map<String, Object> success = getSuccessMap();
        success.put("data", data);
        return success;
    }

    public static Map<String, Object> getErrorMap(String code, String msg) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

}
