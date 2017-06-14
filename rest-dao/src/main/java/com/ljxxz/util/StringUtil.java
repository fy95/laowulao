package com.ljxxz.util;

import java.util.Random;

/**
 * Created by fuzhao on 2015/9/27.
 */
public class StringUtil {
    private static Random mRandom = new Random();
    /**
     * 得到唯一id
     * @return
     */
    public static String generateRandomTimeUUID(){
        return System.currentTimeMillis()+""+mRandom.nextInt(10000);
    }
}
