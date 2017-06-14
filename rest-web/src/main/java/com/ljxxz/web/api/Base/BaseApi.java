package com.ljxxz.web.api.Base;

import com.ljxxz.model.LoginCookie;
import com.ljxxz.web.enumeration.ResultEnum;
import com.ljxxz.web.enumeration.ResultObject;
import com.ljxxz.web.util.CookieUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by fuzhao on 2015/9/21.
 */
public class BaseApi {
    @Context
    protected HttpServletRequest httpServletRequest;
    @Context
    protected HttpServletResponse httpServletResponse;

    protected Response response(ResultEnum status,String msg, Object data) {
        ResultObject resultObject = new ResultObject(status, msg, data);
        return response(resultObject);
    }
    protected Response response(ResultObject resultObject) {
        return Response.status(Response.Status.OK).entity(resultObject).build();
    }
    protected Response okResponse(Object data) {
        return response(ResultEnum.SUCCESS, "", data);
    }
    protected Response response(ResultEnum status) {
        return response(status, "", null);
    }
    protected Response response(ResultEnum status, Object data) {
        return response(status, "", data);
    }

    protected  void addLoginCookie(LoginCookie loginCookie){
        Cookie cookie = CookieUtil.getLoginCookie(loginCookie.toCookieString());
        httpServletResponse.addCookie(cookie);
    }

    protected void addLoginCookie(String loginCookie){
        Cookie cookie = CookieUtil.getLoginCookie(loginCookie);
        httpServletResponse.addCookie(cookie);
    }
}
