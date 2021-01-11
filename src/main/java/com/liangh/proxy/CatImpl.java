package com.liangh.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/01/11 17:34
 */
@Slf4j
public class CatImpl implements Cat {

    @Override
    public void bite() {
        log.info("猫正在吃老鼠");
    }
}
