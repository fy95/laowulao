package com.ljxxz.web.enumeration;

/**
 * Created by fuzhao on 2015/9/21.
 */
public enum ResultEnum {

    SUCCESS(0, "ok"),
    USER_ALREADY_EXIST(201,"user already exist"),
    USERNAME_OR_PASSWORD_ERROR(202,"username or password error"),
    UN_LOGIN(402,"user unlogin"),
    UNKNOW_ERROR(504,"unknow server error");


    private int code;
    private String msg;

    private ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
