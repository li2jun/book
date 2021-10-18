package com.test;

import com.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @ahuthor lzy
 * @create 2021-10-06
 */
public class JDBCUtilsTest {

    @Test
    public void getConnection() {
        Connection conn = null;
        conn = JDBCUtils.getConnection();
        System.out.println(conn);
        JDBCUtils.closeConnection(conn);
        System.out.println(conn);
    }

    @Test
    public void closeConnection() {
    }
}