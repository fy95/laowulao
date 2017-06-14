package com.ljxxz.util;


import org.slf4j.Logger;

/**
 * Created by fuzhao on 2015/10/10.
 */
public class LoggerFactory {

    public static Logger getLoggerByName(String loggerName) {
        return org.slf4j.LoggerFactory.getLogger(loggerName);
    }
    public static Logger getMainLogger() {
        return org.slf4j.LoggerFactory.getLogger("main");
    }
}