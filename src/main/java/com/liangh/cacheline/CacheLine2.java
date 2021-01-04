package com.liangh.cacheline;


import sun.misc.Contended;

public class CacheLine2 {

    // 加了此注解，可以保证两个变量在不同的缓存行上
    @Contended
    volatile long x;

    // jdk7之前缓存行的解决办法是定义很多的变量，强行将两个变量分散到不同的缓存行上
//    long a1,a2,a3,a4,a5,a6,a7,a8;

    @Contended
    volatile long y;

    public static void main(String[] args) throws Exception {

        for (int m = 0; m < 10 ; m++) {



            CacheLine2 a = new CacheLine2();

            Thread t1 = new Thread(()->{
                for (long i = 0; i < 10000_0000L; i++) {
                    a.x = i;
                }
            });

            Thread t2 = new Thread(()->{
                for (long i = 0; i < 10000_0000L; i++) {
                    a.y = i;
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
}
