package com.ljxxz.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by fuzhao on 2015/9/26.
 */
public class AppConfig {

    private static final String QINIUYUN_AK = "0lvJ95GGfmO3oeat21t-hE2gYwRFr7U23HgKJpUZ";
    private static final String QINIUYUN_SK = "oyP7lTb7t_ggbiTTbMNS6Mvgn5l4npxmUyYmMTof";
    private static final String QINIUYUN_BUCKET = "ljxxz-app";
    private static final String QINIUYUN_DOMAIN = "7xn2ny.com1.z0.glb.clouddn.com";

    private static Properties properties = new Properties();

    static{
        String file = AppConfig.class.getClassLoader().getResource("").getPath() + File.separator + "config.properties";
        _load_config_file(file);
    }

    private static void _load_config_file(String file){
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(new File(file));
            properties.load(inputStream);
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            try{
                if(inputStream != null){
                    inputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static int getCookieExpireDay() {
        return Integer.valueOf(properties.getProperty("cookie.expire", "10"));
    }

    public static String getServerDomain() {
        return properties.getProperty("server.domain", "127.0.0.1");
    }

    public static String getQiniuyunAk() {
        return properties.getProperty("qiniuyun.AK",QINIUYUN_AK);
    }

    public static String getQiniuyunSk() {
        return properties.getProperty("qiniuyun.SK",QINIUYUN_SK);
    }

    public static String getQiniuyunBucket() {
        return properties.getProperty("qiniuyun.bucket",QINIUYUN_BUCKET);
    }

    public static String getQiniuyunDomain() {
        return properties.getProperty("qiniuyun.domain",QINIUYUN_DOMAIN);
    }
}
