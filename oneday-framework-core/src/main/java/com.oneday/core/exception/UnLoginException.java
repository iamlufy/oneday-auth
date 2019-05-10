package com.oneday.core.exception;

import com.oneday.core.global.IBaseCode;
import lombok.Getter;

/**
 * @author zzf
 */
public class UnLoginException  extends RuntimeException{
    @Getter
    private IBaseCode iBaseCode;
    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(Throwable cause) {
        super(cause);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginException() {

    }

    public UnLoginException(IBaseCode iBaseCode) {
        super(iBaseCode.getMessage());
        this.iBaseCode = iBaseCode;
    }
    
}
