package com.liangh.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/24 15:24
 */
@Slf4j
public class InheritableThreadLocalTest {

    public static void main(String[] args) throws Exception{

        InheritableThreadLocal threadLocal = new InheritableThreadLocal();

        threadLocal.set("abc");

        new Thread(() -> {

            try {
                log.info("子程序中的值：{}", threadLocal.get());

                Thread.sleep(200);

                // 应该还是abc
                log.info("查看子程序中的值是否改变：{}", threadLocal.get());
            }catch (Exception e){
                log.error("", e);
            }

        }).start();

        Thread.sleep(100);
        threadLocal.set("main-1");
        log.info("主线程读取值：{}", threadLocal.get());



    }

}
