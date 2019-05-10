package com.oneday.core.global;


import lombok.Getter;
import com.oneday.core.global.IBaseCode;
import org.springframework.http.HttpStatus;

/**
 * 全局码 1或者0或者1XX
 * Created by zzf on 2017/1/15.
 */
@Getter
public enum Code implements IBaseCode {
    OPERATION_SUCCESS(1, "操作成功",HttpStatus.OK),
    OPERATION_Fail(0, "操作失败",HttpStatus.OK),
    EXCEPTION(110,"系统异常",HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_PARAM(102, "参数错误",HttpStatus.BAD_REQUEST),
    NULL_POINT(103, "查找不到数据",HttpStatus.BAD_REQUEST);
    private int code;
    private String message;
    private HttpStatus httpStatus;
    Code(int code, String message,HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
