package com.oneday.auth.authentication.infrastructure.dataobject;

import com.oneday.auth.authentication.client.enums.EncryptionAlgorithm;
import com.oneday.core.tenant.TenantIdDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.oneday.auth.authentication.infrastructure.dataobject.LoginUserDO.TABLE_NAME;

/**
 * 登录账户DO类
 *
 * @author zhuangzf
 * @date 2019/3/28 14:07
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = TABLE_NAME,uniqueConstraints = @UniqueConstraint(name = "uni_tenant_user", columnNames ={"tenantId","userId"}))
public class LoginUserDO extends TenantIdDO {
    public static final String TABLE_NAME = "tb_login_user";

    @Column(columnDefinition = "varchar(40) not null comment'登录账户名'")
    private String username;

    @Column(columnDefinition = "varchar(100) not null comment'密码'")
    private String password;

    @Column(columnDefinition = "varchar(100) not null comment'盐'")
    private String salt;

    @Column(columnDefinition = "varchar(10) not null comment'加密算法'")
    @Enumerated(EnumType.ORDINAL)
    private EncryptionAlgorithm encryptionAlgorithm ;

    @Column(columnDefinition = "varchar(30) not null comment'业务唯一ID'")
    private String userId;

}
