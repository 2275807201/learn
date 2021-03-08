package com.liangh.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/08 16:04
 */
@Slf4j
public class ParallelStreamTest {

    public static void main(String[] args) {

        // 并行流测试
        new ParallelStreamTest().test();

    }

    private void test() {
        List list = new ArrayList();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        List collect = (List) list.parallelStream().map(a -> {
            log.info("当前线程：{},处理的元素:{}", Thread.currentThread(), a);
            return (((Integer) a) * ((Integer) a));
        }).collect(Collectors.toList());

        log.info("转换后的结果：{}", collect.size());

    }

}
