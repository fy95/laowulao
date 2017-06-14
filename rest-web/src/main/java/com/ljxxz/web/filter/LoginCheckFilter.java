package com.ljxxz.web.filter;

import com.ljxxz.model.LoginCookie;
import com.ljxxz.util.LoggerFactory;
import com.ljxxz.web.enumeration.ResultEnum;
import com.ljxxz.web.enumeration.ResultObject;
import com.ljxxz.web.filter.annotation.LoginCheck;
import org.slf4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
@LoginCheck
public class LoginCheckFilter implements ContainerRequestFilter{
    public static final Logger logger = LoggerFactory.getMainLogger();
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("---------LoginCheck---------");
        Map<String,Cookie> map = containerRequestContext.getCookies();
        Cookie cookie = map.get(LoginCookie.LOGIN_COOKIE);
        String cookieStr = cookie == null?"":cookie.getValue();
        //TODO 解析cookie，此处需告知客户端传cookie的格式
        LoginCookie loginCookie = null;
        try {
            loginCookie = new LoginCookie(cookieStr);
            long userId = loginCookie.getUserId();
            String userName = loginCookie.getUserName();
            String password = loginCookie.getPassword();
        }catch(Exception e){
            System.out.println("---------Login check fail, please send request with cookie----------");
            logger.warn("parse cookie:{} exceptioin:{}",cookieStr,e.getMessage());
        }
        //未登录，拦截请求。
        if(loginCookie == null)
            containerRequestContext.abortWith(Response.status(ResultEnum.UN_LOGIN.getCode()).entity(new ResultObject(ResultEnum.UN_LOGIN)).build());
    }
}
