package com.service;

import com.bean.User;

/**
 * @ahuthor lzy
 * @create 2021-10-06
 */
public interface UserService {
    //注册用户
    public void registerUser(User user);
    //登录
    public User login(User user);
    //检查该用户名是否可用
    public boolean existsUsername(String username);

}
