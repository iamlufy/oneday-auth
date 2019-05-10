package com.oneday.core.exception;

import lombok.Getter;
import com.oneday.core.global.IBaseCode;

/**
 * 数据库错误
 *
 * @author zzf
 */
@Getter
public class DataBaseException extends BaseException {

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(IBaseCode iBaseCode, Error error) {
        super(iBaseCode, error);
    }

    public DataBaseException(Error error) {
        super(error);
    }

    public DataBaseException(Error error,String msg) {
        super(error, msg);
    }
    public enum Error {
        /**
         *
         */
        UPDATE,
        INSERT,
        DELETE,
        QUERY,;

    }
}
