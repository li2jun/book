package com.test;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @ahuthor lzy
 * @create 2021-10-06
 */
public class UserServiceTest {
    UserService us = new UserServiceImpl();
    @Test
    public void registerUser() {
        User user = new User(null,"lzy","960810","958751567@qq.com");
        us.registerUser(user);
    }

    @Test
    public void login() {
        User user = new User(null,"lzy","96081000","958751567@qq.com");
        User user1 = us.login(user);
        if(user1!=null){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

    }

    @Test
    public void existsUsername() {
        if(us.existsUsername("lzyy")){
            System.out.println("该用户名已被使用");
        }else{
            System.out.println("该用户名可以使用");
        }
    }
}