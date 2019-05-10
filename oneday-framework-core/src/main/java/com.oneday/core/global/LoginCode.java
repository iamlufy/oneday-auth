package com.oneday.core.global;

import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Map;

/**
 * 1XX
 *
 * @author zzf
 * @date 2017/4/30
 */
public enum LoginCode implements IBaseCode {
    UN_LOGIN(100,"用户未登陆",HttpStatus.OK),

    USER_NO_EXIST(101,"用户不存在",HttpStatus.NOT_FOUND),
    INCORRECT(102,"用户名或者密码错误",HttpStatus.OK),
    USER_EXIST(103,"该用户已被注册",HttpStatus.OK),
    PHONECODE_ERROR(104,"手机验证码错误",HttpStatus.OK),
    REJECT(105, "该账户已过期或者被禁用",HttpStatus.UNAUTHORIZED), SUCCESS(106, "允许登录",HttpStatus.OK);
    private int code;
    private String msg;
    private HttpStatus httpStatus;
    LoginCode(int code, String msg,HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
    @Override
    public int getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = Maps.newHashMap();
        Arrays.stream(LoginCode.values()).forEach(loginCode -> map.put(loginCode.getCode(), loginCode.getMessage()));
        return map;
    }
}
