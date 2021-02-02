package com.liangh.concurrent;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/02/02 11:30
 */
@Slf4j
public class CountDownLatchTest {

    public static void main(String[] args) throws Throwable {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 2; i++) {

            new Thread(()->{

                try {

                    log.info("线程开始等待");
                    countDownLatch.await();
                    log.info("线程等待完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // 主线程阻塞
        System.in.read();

        // 倒计时
        countDownLatch.countDown();
    }

}
