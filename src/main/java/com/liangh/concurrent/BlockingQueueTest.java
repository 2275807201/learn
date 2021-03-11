package com.liangh.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/11 09:05
 */
@Slf4j
public class BlockingQueueTest {

    public static void main(String[] args) throws Exception {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);


        new Thread(() -> {
//            try {
//                blockingQueue.put(1);
//            } catch (InterruptedException e) {
//                log.error("", e);
//            }
        }).start();


        Integer take = blockingQueue.take();
        log.info("消费数字:{}", take);


    }

}
