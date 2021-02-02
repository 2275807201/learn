package com.liangh.concurrent;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/02/02 10:25
 */
@Slf4j
public class CopyOnWriteArraySetTest {

    private HashSet hashSet = new HashSet();

    // 线程安全集合
    private CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

    // 原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);


    public static void main(String[] args) throws Exception {

        CopyOnWriteArraySetTest test = new CopyOnWriteArraySetTest();
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    int value = test.atomicInteger.getAndIncrement();
//                    test.hashSet.add(value);
                    test.copyOnWriteArraySet.add(value);
                }
                log.info("线程结束");
            });
            t1.start();
        }

        log.info("线程启动完");

        // 阻塞主程序
        System.in.read();

        // 查看集合数量
        log.info("hashSet's num is :{}\n,copyOnWriteSet's num is :{}",
                test.hashSet.size(), test.copyOnWriteArraySet.size());
    }
}
