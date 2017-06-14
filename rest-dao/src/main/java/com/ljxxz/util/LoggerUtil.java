package com.ljxxz.util;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

/**
 * Created by fuzhao on 2015/9/27.
 */
public class LoggerUtil {

    static{
        //初始化日志文件路径
        String logFile = LoggerUtil.class.getClassLoader().getResource("").getPath() + "log4j.properties";
        PropertyConfigurator.configure(logFile);
    }

    public static void logErr(String info,Class clazz){
        Log log = LogFactory.getLog(clazz);
        log.error(info);
    }

}
