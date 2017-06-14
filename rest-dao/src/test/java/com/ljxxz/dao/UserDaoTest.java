package com.ljxxz.dao;

import com.ljxxz.model.User;
import junit.framework.Assert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by fuzhao on 2015/9/21.
 */
public class UserDaoTest{

//    @Test
//    public void findUserById() {
//        SqlSession sqlSession = getSessionFactory().openSession();
//        UserDao userMapper = sqlSession.getMapper(UserDao.class);
//        User user = userMapper.getUserById(1);
//        Assert.assertNotNull("没找到数据", user);
//    }
//
//    //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
//    private static SqlSessionFactory getSessionFactory() {
//        SqlSessionFactory sessionFactory = null;
//        String resource = "mybatis/mybatis-configuration.xml";
//        try {
//            sessionFactory = new SqlSessionFactoryBuilder().build(Resources
//                    .getResourceAsReader(resource));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sessionFactory;
//    }
}
