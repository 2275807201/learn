package com.liangh.concurrent;

import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/11 09:35
 */
@Slf4j
public class LockSupportTest {


    public static void main(String[] args) throws Exception {
        LockSupportTest lockSupportTest = new LockSupportTest();


        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            log.info("程序执行完的方法");
        }));

//        System.in.read();

        lockSupportTest.lockOccupy();
    }

    //
    private void lockOccupy() {

        Object o1 = new Object();

        Thread thread = Thread.currentThread();

        new Thread(() -> {
            LockSupport.unpark(thread);
            log.info("解除阻塞");
        }).start();

        LockSupport.park();
        log.info("阻塞后执行");
    }


    public static void t2() throws Exception
    {
        Thread t = new Thread(new Runnable()
        {
            private int count = 0;

            @Override
            public void run()
            {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000)
                {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //等待或许许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(2000);

        // 中断线程
        t.interrupt();


        System.out.println("main over");
    }

}
