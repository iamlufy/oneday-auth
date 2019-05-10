package com.oneday.core.base;

import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author zzf
 */
@Setter
public  class BasePage implements Serializable {

    public static final int DEFAULT_PAGE_NUM = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;

    private Integer pageSize;
    private Integer pageNum;

    public Integer getPageNum() {
        this.pageNum = Optional.ofNullable(this.pageNum).orElse(DEFAULT_PAGE_NUM);
        return this.pageNum > 0 ? this.pageNum - 1 : this.pageNum;
    }


    public Integer getPageSize() {
        this.pageSize = Optional.ofNullable(this.pageSize).orElse(DEFAULT_PAGE_SIZE);
        return this.pageSize;
    }

    public  PageRequest page() {
        return PageRequest.of(this.getPageNum(), this.getPageSize());
    }

    public static PageRequest init() {
        return PageRequest.of(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE);
    }
}
