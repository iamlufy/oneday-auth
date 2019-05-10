package com.oneday.core;

import org.springframework.data.domain.PageRequest;

import java.io.Serializable;

/**
 * @author zzf
 */
public interface PageAble extends Serializable {

   default PageRequest page(){
       return PageRequest.of(0, 10);
   }

    Integer getPageNum();

    Integer getPageSize();

    default PageRequest getPage() {
        return PageRequest.of(getPageNum() == 0 ? getPageNum() : getPageNum() - 1, getPageSize());
    }

}
