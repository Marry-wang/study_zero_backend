package com.demo.context;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmengwei
 */
public class SecurityContextHolder {

    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object value)
    {
        Map<String, Object> map = getLocalMap();
        map.put(key, value == null ? null : value);
    }

    public static String get(String key)
    {
        Map<String, Object> map = getLocalMap();
        return map.getOrDefault(key, null).toString();
    }

    public static <T> T get(String key, Class<T> clazz)
    {
        Map<String, Object> map = getLocalMap();
        return (T)map.getOrDefault(key, null);
    }

    public static Map<String, Object> getLocalMap()
    {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null)
        {
            map = new ConcurrentHashMap<String, Object>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap)
    {
        THREAD_LOCAL.set(threadLocalMap);
    }


}
