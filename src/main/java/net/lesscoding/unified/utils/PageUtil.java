package net.lesscoding.unified.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author eleven
 * @date 2021/3/20-14:31
 * @apiNote 一对多分页工具类
 */
public class PageUtil<T> {
    public Page<T> getPage(T t, List<T> data, Function<T, ? extends Number> currentGetter, Function<T, ? extends Number> sizeGetter) {
        long currentPage = Long.parseLong(String.valueOf(currentGetter.apply(t)));
        long pageSize = Long.parseLong(String.valueOf(sizeGetter.apply(t)));
        return pagination(data, currentPage, pageSize);
    }

    private static <T> Page<T> pagination(List<T> data, long currentPage, long pageSize) {
        Page<T> page = new PageDTO<>();
        long fromIndex = 0;
        long from = (currentPage - 1) * pageSize;
        fromIndex = from > data.size() ? data.size() : from;
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        page.setTotal(data.size());
        List<T> records = data.stream()
                .skip(fromIndex)
                .limit(pageSize)
                .collect(Collectors.toList());
        page.setRecords(records);
        return page;
    }

    public static <T> Page<T> getPageByGetter(List<T> data, Getter<T, Long> currentGetter, Getter<T, Long> sizeGetter) {
        long currentPage = currentGetter.apply();
        long pageSize = sizeGetter.apply();
        return pagination(data, currentPage, pageSize);
    }

    @FunctionalInterface
    public interface Getter<T, R> {
        R apply();
    }
}