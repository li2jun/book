package com.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @ahuthor lzy
 * @create 2021-10-09
 */
public class WebUtils {
    public static <T> T copyParaToBean(Map map, T t) {
        try {
            BeanUtils.populate(t, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static int stringToInterge(String str, int defaultInt) {
        if (str == null || str == "") {
            return defaultInt;
        } else  {
            return Integer.parseInt(str);
        }
    }
}
