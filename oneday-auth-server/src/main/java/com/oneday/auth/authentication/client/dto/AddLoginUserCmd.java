package com.oneday.auth.authentication.client.dto;

import com.oneday.auth.authentication.client.enums.EncryptionAlgorithm;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 增加登录用户命令
 *
 * @author zhuangzf
 * @date 2019/3/29 15:42
 */
@Data
public class AddLoginUserCmd implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String userId;

    @NotNull
    private String tenantId;

    private EncryptionAlgorithm encryptionAlgorithm;
}
