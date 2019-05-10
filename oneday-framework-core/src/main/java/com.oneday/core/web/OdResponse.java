package com.oneday.core.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.oneday.core.global.Code;
import com.oneday.core.global.IBaseCode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author zhuangzf
 * @date 2018/12/25 10:12
 */
@Data
public class OdResponse<T> {
    private T params;
    private Integer code;
    private String message;
    @JsonIgnore
    private HttpStatus httpStatus;

    private OdResponse(IBaseCode iBaseCode) {
        this.setCode(iBaseCode.getCode());
        this.setMessage(iBaseCode.getMessage());
        this.setHttpStatus(iBaseCode.getHttpStatus());
    }

    private OdResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public static <T> HttpEntity<OdResponse<T>> ofSuccess(T params) {
        OdResponse<T> resp = new OdResponse<>(Code.OPERATION_SUCCESS);
        resp.setParams(params);
        return  ResponseEntity.status(resp.getHttpStatus()).body(resp);
    }
    public static HttpEntity<OdResponse> ofSuccess() {
        OdResponse resp = new OdResponse(Code.OPERATION_SUCCESS);
        return  ResponseEntity.status(resp.getHttpStatus()).body(resp);
    }
    public static HttpEntity<OdResponse> ofSuccess(Integer code, String message) {
        OdResponse resp = new OdResponse(code, message);
        return  ResponseEntity.status(resp.getHttpStatus()).body(resp);
    }

    public static HttpEntity<OdResponse> ofFail(IBaseCode iBaseCode) {
        OdResponse resp = new OdResponse(iBaseCode);
        return  ResponseEntity.status(resp.getHttpStatus()).body(resp);
    }

    public static HttpEntity ofCreated() {
        OdResponse resp = new OdResponse(Code.OPERATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

}
