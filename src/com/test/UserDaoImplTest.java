package com.test;

import com.bean.User;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @ahuthor lzy
 * @create 2021-10-06
 */
public class UserDaoImplTest {
    UserDao udi = new UserDaoImpl();

    @Test
    public void queryByUsername() {
        System.out.println(udi.queryByUsername("admin"));
    }

    @Test
    public void queryByUsernameAndPassword() {
        System.out.println(udi.queryByUsernameAndPassword("admin", "admin"));
    }

    @Test
    public void register() {

        User user = new User(null,"admin","admin","admin@163.com");
        System.out.println(udi.register(user));
    }
}