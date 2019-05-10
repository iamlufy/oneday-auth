package com.oneday.core.web;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzf
 */
@Data
public class Request<T>  implements Serializable {
    private T params;

    private String msg;

    private String token;

    private Object ext;

    private String requestId;
    private String time;
    private String caller;
    private String api;



}
