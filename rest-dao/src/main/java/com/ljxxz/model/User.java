package com.ljxxz.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by fuzhao on 2015/9/21.
 */
@XmlRootElement
public class User implements Serializable {
    /**类标记*/
    public static final String TAG = "USER";
    private long userId;
    private String userExtraId;
    private String userType;
    private String userName;
    private String password;
    private String createTime;
    private String mobile;
    private String gender;
    private String age;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserExtraId() {
        return userExtraId;
    }

    public void setUserExtraId(String userExtraId) {
        this.userExtraId = userExtraId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
