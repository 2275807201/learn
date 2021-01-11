package com.liangh.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/01/11 16:25
 */
@Slf4j
public class DogImpl implements Dog {

    @Override
    public String eat(String food) {
        log.info("狗正在吃: {}", food);
        return food + "好吃";
    }
}
