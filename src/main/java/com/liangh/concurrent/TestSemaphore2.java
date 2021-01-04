package com.liangh.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import javax.naming.InitialContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSemaphore2 {

    public static void main(String[] args) {

        // 测试信号量的release方法是否由别的线程释放
        Semaphore semaphore = new Semaphore(5);

        for (int i =0; i< 15; i++){
            new Thread(() -> {

                try {
                    log.info("1请求获取信号量");
                    semaphore.acquire();
                    log.info("2成功获取信号量");

                    Thread.sleep(600 * 1000);


                }catch (Throwable e){
                    log.error("", e);
                }


            }).start();
        }

        new Thread(() -> {
//            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(5000);
                    semaphore.release(5);
                }catch (Exception e){
                    log.error("", e);
                }
//            }
        }).start();

    }

}