package com.liangh.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/10 10:59
 */
@Slf4j
public class BreakTest {

    public static void main(String[] args) {

        ok:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(j == 2){
                    log.info("i:{}, j:{}", i, j);
                    break ok;
                }
            }
        }
    }

}
