package com.oss.tool.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * 检验工具类
 */
public class ValidateUtil {
    public ValidateUtil(){
    }

    public static boolean isCountEmpty(Integer integer) {
        return  integer == null || 0==integer;
    }

    public static boolean isNotCountEmpty(Integer integer) {
        return  !isCountEmpty(integer);
    }

    public static boolean isCountEmpty(Long l) {
        return  l == null || 0==l;
    }

    public static boolean isNotCountEmpty(Long l) {
        return  !isCountEmpty(l);
    }

    public static boolean isEmpty(String str) {
        return  str == null || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return  !isEmpty(str);
    }

    public static boolean isEmpty(Integer integer) {
        return  integer == null ;
    }

    public static boolean isNotEmpty(Integer integer) {
        return  !isEmpty(integer);
    }
    public static boolean isEmpty(Long l) {
        return  l == null ;
    }

    public static boolean isNotEmpty(Long l) {
        return  !isEmpty(l);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (null == object) {
            return true;
        }

        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);

                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));

                if (f.get(object) != null && isEmpty(f.get(object).toString())) {
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}
