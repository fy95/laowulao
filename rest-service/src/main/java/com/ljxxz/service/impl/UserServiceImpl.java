package com.ljxxz.service.impl;

import com.ljxxz.dao.UserDao;
import com.ljxxz.model.LoginCookie;
import com.ljxxz.service.intf.UserService;
import com.ljxxz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuzhao on 2015/9/21.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public User getUserByName(String userName){
        return userDao.getUserByName(userName);
    }

    public String show(){
        return "hello this is spring";
    }

    public Map<String,String> addUser(User user){
        Map<String,String> map = new HashMap<String,String>();
        User temp = getUserByName(user.getUserName());
        if(temp != null){
            //用户已存在 -2标记
            map.put("code","-2");
            return map;
        }
        long userId = userDao.addUser(user);
        user.setUserId(userId);
        map.put("code","0");
        map.put("userId",userId+"");
        map.put(LoginCookie.LOGIN_COOKIE,__general_login(user));
        return map;
    }

    public Map<String,String> loginUser(User user){
        Map<String,String> map = new HashMap<String,String>();
        String userName = user.getUserName();
        String password = user.getPassword();
        User temp = getUserByName(userName);
        if(temp == null || !password.equals(temp.getPassword())){
            map.put("code","-1");
            map.put("msg","username or password is not correct");
            return map;
        }

        map.put("code","0");
        map.put("userId",temp.getUserId()+"");
        map.put(LoginCookie.LOGIN_COOKIE,__general_login(temp));

        return map;
    }

    public String __general_login(User user){
        LoginCookie loginCookie = new LoginCookie(user);
        return loginCookie.toCookieString();
    }
}
