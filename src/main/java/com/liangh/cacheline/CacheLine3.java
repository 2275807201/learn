package com.liangh.cacheline;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/14 14:48
 */
public class CacheLine3 {

    public static volatile long[] arr = new long[16];
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 10000_0000L; i++) {
                arr[0] = i;
            }
        });
        Thread t2 = new Thread(()->{
            for (long i = 0; i < 10000_0000L; i++) {
                arr[8] = i;
            }
        });
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }

}
