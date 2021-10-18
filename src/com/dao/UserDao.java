package com.dao;

import com.bean.User;

/**
 * @ahuthor lzy
 * @create 2021-10-06
 */
public interface UserDao {
    public User queryByUsername(String username);
    public User queryByUsernameAndPassword(String username, String password);
    public int  register(User user);

}
