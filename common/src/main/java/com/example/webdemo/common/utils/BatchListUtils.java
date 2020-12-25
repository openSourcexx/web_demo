package com.example.webdemo.common.utils;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

/**
 * 分批工具
 * @author tangaq
 * @date 2020/12/24
 */
public class BatchListUtils {
    public static final int DEFAULT_SIZE_500 = 500;
    public static final int DEFAULT_SIZE_1K = 1000;
    public static final int DEFAULT_SIZE_1W = 10000;

    private BatchListUtils() {
    }

    /**
     * @param origin 元数据
     * @param batchSize 批量
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> partition(List<T> origin, int batchSize) {
        Iterable<List<T>> iterable = Iterables.partition(origin, batchSize);
        List<List<T>> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

}
