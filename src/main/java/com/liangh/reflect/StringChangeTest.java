package com.liangh.reflect;

import java.lang.reflect.Field;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/10 15:09
 */
public class StringChangeTest {

    public static void main(String[] args) throws Exception {

        // 创建字符串"Hello World"， 并赋给引用s
        String s = "Hello World";

        // Hello World
        System.out.println("s = " + s);

        // 获取String类中的value字段
        Field valueFieldOfString = String.class.getDeclaredField("value");

        // 改变value属性的访问权限
        valueFieldOfString.setAccessible(true);

        // 获取s对象上的value属性的值
        char[] value = (char[]) valueFieldOfString.get(s);

        // 改变value所引用的数组中的第5个字符
        value[5] = '@';
        System.out.println("s = " + s); // Hello_World


    }

}
