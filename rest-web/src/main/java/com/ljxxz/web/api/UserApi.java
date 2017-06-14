package com.ljxxz.web.api;

import com.ljxxz.model.LoginCookie;
import com.ljxxz.model.User;
import com.ljxxz.service.intf.UserService;
import com.ljxxz.util.AppConfig;
import com.ljxxz.web.api.Base.BaseApi;
import com.ljxxz.web.enumeration.ResultEnum;
import com.ljxxz.web.filter.annotation.LoginCheck;
import com.ljxxz.web.util.CookieUtil;
import com.ljxxz.web.util.TextUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.std.ScalarSerializerBase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuzhao on 2015/9/21.
 */
@Path("/user")
public class UserApi extends BaseApi {

    @Autowired
    private UserService userService;

    @GET
    @Produces({"application/json"})
    public Response getUser() {
        User user = userService.getUserById(1);
        System.out.println(user.getUserName());
        System.out.println(userService.show());
        System.out.println("domain: " + AppConfig.getQiniuyunDomain());
        return okResponse(user);
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/x-www-form-urlencoded"})
    @Path("/regist")
    public Response registUser(@FormParam("userType")String userType,
                                @FormParam("userName") String userName,
                               @FormParam("userPsw") String password,
                               @FormParam("userMobile")String mobile
    ) {
        User user = new User();
        user.setUserType(userType);
        user.setUserName(userName);
        user.setPassword(password);
        user.setMobile(mobile);
        Map<String,String> map = userService.addUser(user);
        String code = map.get("code");

        //用户已存在
        if("-2".equals(code)){
            return response(ResultEnum.USER_ALREADY_EXIST,ResultEnum.USER_ALREADY_EXIST.getMsg(),null);
        }

        //登录成功
        if("0".equals(code)){
            //设置cookie
            String cookieStr = map.get(LoginCookie.LOGIN_COOKIE);
            Cookie cookie = CookieUtil.getLoginCookie(cookieStr);
            httpServletResponse.addCookie(cookie);
            return okResponse(map.get("userId"));
        }

        return response(ResultEnum.UNKNOW_ERROR);
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/x-www-form-urlencoded"})
    @Path("/login")
    public Response login(@FormParam("userName") String userName,
                          @FormParam("password") String password){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        Map<String,String> map = userService.loginUser(user);
        String code = map.get("code");
        //用户名密码错误
        if("-1".equals(code)){
            return response(ResultEnum.USERNAME_OR_PASSWORD_ERROR,ResultEnum.USERNAME_OR_PASSWORD_ERROR.getMsg(),null);
        }

        String cookieStr = map.get(LoginCookie.LOGIN_COOKIE);
        Cookie cookie = CookieUtil.getLoginCookie(cookieStr);
        httpServletResponse.addCookie(cookie);
        return okResponse(map.get("userId"));
    }

    @GET
    @Produces({"application/json"})
    @Path("/loginCheckTest")
    @LoginCheck   //对所有需要检查登录情况的api接口使用此注解即可
    public Response testLoginCheck(){
        return okResponse(0);
    }


    @Path("/restService")
    public class RestService {
        @POST
        @Path("/getUserText")
        @Produces(MediaType.TEXT_PLAIN)
        /**
         * 测试返回text文本媒体类型数据的方式
         * @return "Hello,World!"
         */
        public String getUserText() {
            return "Hello,World!";
        }

        @GET
        @Path("/getUserXml")
        @Produces(MediaType.APPLICATION_XML)
        /**
         * 测试返回xml媒体类型数据的方式
         * @return User object
         */
        public User getUserXml() {
            User user = new User();
            user.setUserName("snail");
            user.setAge("22");
            user.setGender("male");
            return  user;
        }

        @GET
        @Path("/getUserJson")
        @Produces(MediaType.APPLICATION_JSON)
        /**
         * 测试返回json媒体类型数据的方式
         * @return User object
         */
        public User getUserJson() {
            User user = new User();
            user.setUserName("snail");
            user.setAge("22");
            user.setGender("male");
            return user;
        }

        @POST
        @Path("/getUserInfo")
        @Produces(MediaType.APPLICATION_JSON)
        /**
         * 测试带请求参数时返回json媒体类型数据的方式
         * @param username
         * @return
         */
        public User getUserInfo(@FormParam("userName") String userName) {
            if (userName == null || "".equals(userName)) {
                return null;
            }
            return getUserByName(userName);
        }

        /**
         * 通过用户名获取用户
         *
         * @param userName
         * @return User object
         */
        private User getUserByName(String userName) {
            HashMap<String, User> map = initAllUsers();
            return map.get(userName);
        }

        /**
         * 获取所有用户的map
         *
         * @return 所有用户的map
         */
        private HashMap<String, User> initAllUsers() {
            HashMap<String, User> map = new HashMap<String, User>();

            User user1 = new User();
            user1.setUserName("Jack");
            user1.setPassword("Jack");
            user1.setAge(18 + "");
            user1.setGender("男");
            map.put(user1.getUserName(), user1);

            User user2 = new User();
            user2.setUserName("Alice");
            user2.setPassword("Alice");
            user2.setAge(18 + "");
            user2.setGender("女");
            map.put(user2.getUserName(), user2);

            User user3 = new User();
            user3.setUserName("Allen");
            user3.setPassword("Allen");
            user3.setAge(20 + "");
            user3.setGender("女");
            map.put(user3.getUserName(), user3);

            return map;
        }

        @POST
        @Path("/login")
        @Produces(MediaType.APPLICATION_JSON)
        /**
         * 用户登录
         * @param username 用户名
         * @param password 密码
         * @return Response object
         */
        public Response login(@FormParam("username") String username,
                              @FormParam("password") String password) {
            if (TextUtil.isEmpty(username) || TextUtil.isEmpty(password)) {
                return null;
            }
            User user = checkUser(username, password);
            if (user == null) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();

            return Response
                    .ok(" {\"status\": 404,\n\"message\": \"error\",\n\"response\": \"\"}")
                    .build();

        }

        /**
         * 验证用户是否存在
         *
         * @param username
         * @param password
         * @return User object
         */
        private User checkUser(String username, String password) {
            HashMap<String, User> map = initAllUsers();
            User user = map.get(username);
            if (user != null) {
                String passwd = user.getPassword();
                if (password.equals(passwd)) {
                    return user;
                }
            }
            return null;
        }

        @POST
        @Path("/getAllUsers")
        @Produces(MediaType.APPLICATION_JSON)
        /**
         * 获取所有用户的集合
         * @return Response object
         */
        public Response getAllUsers() {
            ArrayList<User> list = new ArrayList<User>();
            User user1 = new User();
            user1.setUserName("Jack");
            user1.setPassword("Jack");
            user1.setAge(18 + "");
            user1.setGender("男");
            list.add(user1);

            User user2 = new User();
            user2.setUserName("Alice");
            user2.setPassword("Alice");
            user2.setAge(18 + "");
            user2.setGender("女");
            list.add(user2);

            User user3 = new User();
            user3.setUserName("Allen");
            user3.setPassword("Allen");
            user3.setAge(20 + "");
            user3.setGender("女");
            list.add(user3);

            ObjectMapper mapper = new ObjectMapper();


            return Response
                    .ok(" {\"status\": 404,\n\"message\": \"error\",\n\"response\": \"\"}")
                    .build();
        }
    }
}




