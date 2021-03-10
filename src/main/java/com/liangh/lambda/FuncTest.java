package com.liangh.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/09 09:35
 */
public class FuncTest {

    public static void main(String[] args) {
        Function<List<Integer>, Integer> maxFn = Collections::max;

        Integer apply = maxFn.apply(Arrays.asList(1, 10, 3, 5));

        System.out.println(apply);
    }

}
