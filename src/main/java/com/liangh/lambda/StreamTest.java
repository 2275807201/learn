package com.liangh.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/08 16:04
 */
@Slf4j
public class StreamTest {

    public static void main(String[] args) {


        StreamTest streamTest = new StreamTest();

//        // 并行流测试
//        streamTest.testParallelStream();

        // 测试map方法是在多个循环里执行，还是在单个循环里执行
        streamTest.testIfMultiCycle();

    }

    /**
     * 测试map方法是在多个循环里执行，还是在单个循环里执行
     */
    private void testIfMultiCycle() {

        List<Integer> list = new ArrayList();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        List collect = (List) list.stream()
                .map(a -> {
                    log.info("乘法操作");
                    return a * a;
                }).map(a -> {
                    log.info("加法操作");
                    return a + 1;
                })
                .collect(Collectors.toList());

        log.info("转换后的结果：{}", collect.size());

    }

    /**
     * 并行流测试
     */
    private void testParallelStream() {
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
