package com.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @ahuthor lzy
 * @create 2021-10-13
 */
public class BigDecimalTest {
    @Test
    public void BigDeciamlTest(){
        BigDecimal bigDecimal = new BigDecimal("0");
        BigDecimal bigDecimal1 = new BigDecimal("10");
        bigDecimal = bigDecimal.add(bigDecimal1);
        bigDecimal = bigDecimal.add(bigDecimal1);
        bigDecimal = bigDecimal.add(bigDecimal1);
        System.out.println(bigDecimal);
    }
}
