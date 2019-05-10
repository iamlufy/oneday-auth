package com.oneday.auth.authentication.domain.entity;

import com.oneday.auth.authentication.client.enums.EncryptionAlgorithm;
import com.oneday.auth.authentication.client.enums.LoginCode;
import com.oneday.core.base.Unique;
import com.oneday.core.exception.BaseException;
import com.oneday.core.exception.UnLoginException;
import com.oneday.core.tenant.TenantIdV;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.persistence.Embedded;

/**
 * 登录领域类
 *
 * @author zhuangzf
 * @date 2019/3/28 14:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginUserE extends Unique {
    public static final String COMMON_SALT = "commonSalt";
    /**
     * 登录用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;

    /**
     * 加密算法
     */
    private EncryptionAlgorithmV encryptionAlgorithmV;

    /**
     * 业务唯一ID
     */
    private String userId;

    private TenantIdV tenantIdV;

    /**
     * 比较密码
     *
     * @param sendPwd 传入的密码
     * @return true/false
     */
    public void login(String sendPwd) {

        //检查available
        //错误次数限制
        //锁号 ip
        boolean isCheckSuccess = StringUtils.equals(password, encryptionAlgorithmV.getPasswordEncoder().encoder(sendPwd, salt));
        if (!isCheckSuccess) {
            throw new BaseException(LoginCode.CHECK_ERROR);
        }
    }

    /**
     * 密码加密
     */
    public void encryptPassword() {
        this.setSalt(RandomStringUtils.randomNumeric(8));
        this.setPassword(encryptionAlgorithmV.getPasswordEncoder().encoder(password, salt));
    }

}
