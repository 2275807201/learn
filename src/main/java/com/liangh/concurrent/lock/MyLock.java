package com.liangh.concurrent.lock;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/02/02 09:43
 */
@Slf4j
public class MyLock extends AbstractQueuedSynchronizer {

    private CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

    @Override
    protected boolean isHeldExclusively() {
        return true;
    }

    @Override
    protected boolean tryAcquire(int arg) {
        if(copyOnWriteArraySet.contains(arg)){
            // 锁已经被占用
            return false;
        }

        /**
         * copyOnWriteArraySet.add单个方法是线程安全的,
         * 而且添加时是 double-check-lock,
         * 所以同一时刻只有一个线程能添加成功，也就是添加成功的线程获取了锁
         */
        return copyOnWriteArraySet.add(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return copyOnWriteArraySet.remove(arg);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }


    /**
     * 重写的方法
     * isHeldExclusively
     * tryAcquire
     * tryRelease
     * tryAcquireShared
     * tryReleaseShared
     */

    public static void main(String[] args) {

        MyLock myLock = new MyLock();

        /**
         * 解锁线程
         */
        new Thread(() -> {

            try {
                // 阻塞测试线程
                System.in.read();

                // 释放锁
                boolean releaseResult = myLock.release(1);
                log.info("测试线程释放锁的结果:{}", releaseResult);

            } catch (IOException e) {
                log.error("", e);
            }
        }).start();

        // 锁定
        myLock.acquire(1);
        try {

            // 再次锁定，应该获取不到锁，阻塞住；需要上面的解锁线程执行，释放掉锁才行
            myLock.acquire(1);

            log.info("todo something");
        }finally {
            // 释放锁
            boolean releaseResult = myLock.release(1);
            log.info("释放锁的结果:{}", releaseResult);
        }
    }



}
