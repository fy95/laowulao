package com.ljxxz.service.intf;

import com.ljxxz.model.User;

import java.util.Map;

/**
 * Created by fuzhao on 2015/9/21.
 */
public interface UserService {

    public User getUserById(long id);

    public User getUserByName(String userName);

    public Map<String,String> addUser(User user);

    public Map<String,String> loginUser(User user);

    public String show();

}
