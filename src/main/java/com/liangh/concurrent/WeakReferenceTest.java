package com.liangh.concurrent;

import java.lang.ref.WeakReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeakReferenceTest {

    // 存储名称
    private volatile ThreadLocal<String> name;


    public static void main(String[] args) {
        new WeakReferenceTest().test();
    }

    // ThreadLocal测试
    private void test2() {

        WeakReferenceTest weakReferenceTest = new WeakReferenceTest();
        weakReferenceTest.name = new ThreadLocal<>();

        Thread t1 = new Thread(() -> {
            weakReferenceTest.name.set("abc");
            String s = weakReferenceTest.name.get();
            log.info("线程名字打印:{}", s);
        });
        t1.setName("t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            log.info("清除强引用111");
            // 清除强引用
            weakReferenceTest.name = null;
            System.gc();
            log.info("清除强引用222");
        });
        t2.setName("t2");
        t2.start();

    }



    private void test() {
        Person person = new Person();
        person.name = "liangh";

        WeakReference<Person> personWeakReference = new WeakReference<>(person);

        // 清除强引用
        person = null;

        // 垃圾收集
        System.gc();

        log.info("弱引用：{}, 被引用的对象: {}", personWeakReference, personWeakReference.get());




    }


    class Person{
        private String name;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
