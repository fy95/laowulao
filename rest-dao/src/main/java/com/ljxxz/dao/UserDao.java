package com.ljxxz.dao;

import com.ljxxz.model.User;

/**
 * Created by fuzhao on 2015/9/21.
 */
public interface UserDao {

    public User getUserById(long userId);

    public User getUserByName(String userName);

    public long addUser(User user);
}
