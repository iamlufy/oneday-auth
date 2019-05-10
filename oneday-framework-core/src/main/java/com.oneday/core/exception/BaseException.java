package com.oneday.core.exception;

import lombok.Getter;
import com.oneday.core.global.IBaseCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zzf
 */
@Getter
public class BaseException extends RuntimeException {
    protected IBaseCode iBaseCode;
    protected DataBaseException.Error error;
    protected String message;

    BaseException(String message) {
        super(message);
    }

    public BaseException(IBaseCode iBaseCode) {
        super(iBaseCode.getMessage());
        this.iBaseCode = iBaseCode;
    }

    BaseException(IBaseCode iBaseCode, DataBaseException.Error error) {
        this.iBaseCode = iBaseCode;
        this.error = error;
    }

    BaseException(DataBaseException.Error error) {
        this.error = error;
    }

    BaseException(DataBaseException.Error error, String msg) {
        this.error = error;
        this.message = msg;
    }

    public IBaseCode getIBaseCode() {
        return this.iBaseCode;
    }
}
