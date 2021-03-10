package com.liangh.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/10 10:41
 */
@Slf4j
public class OperateTest {


    public static void main(String[] args) {
        OperateTest operateTest = new OperateTest();

        // 短路操作
        operateTest.shortCircuit();

        // 非短路操作
        operateTest.nonShortCircuit();
    }

    /**
     * 非短路操作
     */
    private void nonShortCircuit() {
        int a = 1;

        // 非短路与，a的值不会改变
        boolean result = false & a++ > 0;

        log.info("非短路与后的值：{}", a);
    }

    /**
     * 短路操作
     */
    private void shortCircuit() {

        int a = 1;

        // 短路与，a的值不会改变
        boolean result = false && a++ > 0;

        log.info("短路与后的值：{}", a);
    }

}
