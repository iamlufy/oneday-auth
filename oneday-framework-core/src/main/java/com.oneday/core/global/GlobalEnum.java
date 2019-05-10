package com.oneday.core.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 0XXX
 *
 * @author zzf
 */

@AllArgsConstructor
@Getter
public enum GlobalEnum implements IBaseCode {
    /*
     * 全局状态码
     */
    ID_MUST_BE_NULL(0,"ID_MUST_BE_NULL",HttpStatus.BAD_REQUEST),
    ID_MUST_NOT_BE_NULL(1,"ID_MUST_NOT_BE_NULL",HttpStatus.BAD_REQUEST),
    NON_EXIST(-1, "NON_EXIST", HttpStatus.OK),
    NOT_FOUND(-1, "NOT_FOUND", HttpStatus.BAD_REQUEST),
    OP_FAIL(-1, "OP_FAIL", HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATE(2, "DUPLICATE", HttpStatus.BAD_REQUEST),
    UNEXPECTED_ERROR(500, "未知系统错误", HttpStatus.INTERNAL_SERVER_ERROR), PARAMS_ERROR(400, "参数错误", HttpStatus.BAD_REQUEST),;
    private  int code;
    private  String message;
    private  HttpStatus httpStatus;
}
