package com.ljxxz.model;

/**
 * Created by fuzhao on 2015/9/27.
 */
public class LoginCookie {

    public static final String COOKIE_SEPERATOR = ":";

    public static final String LOGIN_COOKIE="LOGIN_COOKIE";

    private Long userId;

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LoginCookie(long userId,String userName,String password){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public LoginCookie(String loginCookieStr) throws Exception{
        if(loginCookieStr == null || loginCookieStr.length() == 0)throw new Exception("cookie is empty");

        String[] arr = loginCookieStr.split(COOKIE_SEPERATOR);
        if(arr.length < 3)throw new Exception("cookie split error");
        int i = 0;
        this.userId = Long.valueOf(arr[i++]);
        this.userName = arr[i++];
        this.password = arr[i++];
    }

    public LoginCookie(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }

    private String buildWithoutChkSum(){
        StringBuilder builder = new StringBuilder();
        builder.append(userId)
                .append(COOKIE_SEPERATOR)
                .append(userName)
                .append(COOKIE_SEPERATOR)
                .append(password)
                .append(COOKIE_SEPERATOR);
        return builder.toString();
    }

    public String toCookieString(){
        String cookie = buildWithoutChkSum();
        //TODO 此处可将Cookie加密

        return cookie;
    }
}
