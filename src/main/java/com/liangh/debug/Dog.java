package com.liangh.debug;

import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/30 16:15
 */
@Slf4j
public class Dog {

    private String name;

    private int age;

    public void bite(){
        log.info("dog is biting");
        this.age = 10;
    }

    public void run(){
        log.info("dog is running");
    }

    public void eat(){
        log.info("dog has eat");
    }

}
