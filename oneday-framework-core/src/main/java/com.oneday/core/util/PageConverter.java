package com.oneday.core.util;

import com.oneday.core.base.Unique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhuangzf
 * @date 2019/1/7 16:24
 */
@Component
public class PageConverter {
    public <T,D extends Unique> Page<T> pageDoToPageCo(Page<D> page,  Function<D, T > converter) {
        List<T> coList = page.getContent().stream().map(converter).collect(Collectors.toList());
        return new PageImpl<>(coList, page.getPageable(), page.getTotalElements());
    }
}
