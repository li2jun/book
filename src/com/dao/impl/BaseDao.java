package com.dao.impl;

import com.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-06
 */
public abstract class BaseDao {
    private QueryRunner runner = new QueryRunner();
    //执行更新操作
    public int update(String sql, Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return runner.update(conn,sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return -1;
    }
    //返回单个bean对象
    public <T>T queryForOne(String sql,Class<T> type,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return runner.query(conn,sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return null;
    }
    //返回多个bean对象
    public <T> List<T> queryForList(String sql, Class<T> type, Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return runner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return null;
    }
    //返回单个结果集
    public Object queryForSingleValue(String sql, Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return runner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return null;
    }
}
