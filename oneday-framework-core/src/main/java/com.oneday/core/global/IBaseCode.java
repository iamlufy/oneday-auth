package com.oneday.core.global;

import org.springframework.http.HttpStatus;

/**
 * @author zzf
 */
public interface IBaseCode {
    /**
     * 获取操作吗
     *
     * @return
     */
    int getCode();

    /**
     * 获取操作结果信息
     *
     * @return
     */
    String getMessage();

    /**
     * 获取HTTP状态码
     */
    default HttpStatus getHttpStatus(){
        return null;
    }
}
