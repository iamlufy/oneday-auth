package com.oneday.auth.authentication.client.dto;

import com.oneday.auth.authentication.client.enums.EncryptionAlgorithm;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录命令
 *
 * @author zhuangzf
 * @date 2019/3/28 14:54
 */
@Data
public class LoginCmd implements Serializable {
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 业务唯一ID
     */
    private Long userId;

    /**
     * 登录用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密算法
     */
    private EncryptionAlgorithm encryptionAlgorithm;
}
