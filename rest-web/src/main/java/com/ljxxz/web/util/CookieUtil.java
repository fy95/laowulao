package com.ljxxz.web.util;

import com.ljxxz.model.LoginCookie;
import com.ljxxz.util.AppConfig;
import com.ljxxz.util.AppConstants;

import javax.servlet.http.Cookie;

/**
 * Created by fuzhao on 2015/9/27.
 */
public class CookieUtil {

    public static Cookie getLoginCookie(String value){
        Cookie cookie = new Cookie(LoginCookie.LOGIN_COOKIE,value);
        cookie.setMaxAge(AppConfig.getCookieExpireDay()*24*60*60);
        cookie.setDomain(AppConfig.getServerDomain());
        System.out.println(AppConfig.getServerDomain());
        cookie.setPath("/");
        return cookie;
    }
}
