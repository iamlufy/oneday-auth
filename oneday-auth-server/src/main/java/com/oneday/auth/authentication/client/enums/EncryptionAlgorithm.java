package com.oneday.auth.authentication.client.enums;

import com.oneday.core.global.IBaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 加密算法枚举类
 *
 * @author zhuangzf
 * @date 2019/3/28 14:21
 */
@AllArgsConstructor
@Getter
public enum EncryptionAlgorithm {
    /**
     *
     */
    MD5(0, "MD5"),;

    private int code;
    private String value;
}
