package com.oneday.auth.authentication.client.enums;

import com.oneday.core.global.IBaseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author zhuangzf
 * @date 2019/3/29 15:06
 */
@AllArgsConstructor
@Getter
public enum  LoginCode  implements IBaseCode {

    /**
     *
     */
    CHECK_ERROR(1000, "检验不通过", HttpStatus.UNAUTHORIZED),
    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
