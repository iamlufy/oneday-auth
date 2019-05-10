package com.oneday.core.exception;

import com.oneday.core.global.IBaseCode;

/**
 * @author zzf
 */
public class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(IBaseCode iBaseCode) {
        super(iBaseCode);
    }
}
