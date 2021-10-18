package com.dao.impl;

import com.bean.User;
import com.dao.UserDao;

/**
 * @ahuthor lzy
 * @create 2021-10-06
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from user where username = ?";
        return queryForOne(sql,User.class, username);
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from user where username=? and password=?";
        return queryForOne(sql,User.class, username,password);
    }

    @Override
    public int register(User user) {
        String sql = "insert into user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
