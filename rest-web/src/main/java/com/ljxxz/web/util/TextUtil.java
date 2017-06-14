package com.ljxxz.web.util;

/**
 * Created by Administrator on 2016/12/1.
 */
public class TextUtil {
    public static boolean isEmpty(String username) {
        if (username == null || username.length() == 0)
            return true;
        else
            return false;
    }
}
