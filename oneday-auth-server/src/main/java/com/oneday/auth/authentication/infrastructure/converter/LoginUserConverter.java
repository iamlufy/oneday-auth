package com.oneday.auth.authentication.infrastructure.converter;

import com.oneday.auth.authentication.client.dto.AddLoginUserCmd;
import com.oneday.auth.authentication.client.enums.EncryptionAlgorithm;
import com.oneday.auth.authentication.domain.entity.EncryptionAlgorithmV;
import com.oneday.auth.authentication.domain.entity.LoginUserE;
import com.oneday.auth.authentication.infrastructure.dataobject.LoginUserDO;
import com.oneday.core.tenant.TenantIdV;
import com.oneday.core.util.EnumUtil;
import org.springframework.stereotype.Component;

/**
 * @author zhuangzf
 * @date 2019/3/29 15:09
 */
@Component
public class LoginUserConverter {
    public LoginUserE convert2Entity(LoginUserDO loginUserDO) {
        final LoginUserE loginUserE = new LoginUserE();
        loginUserE.setUsername(loginUserDO.getUsername());
        loginUserE.setPassword(loginUserDO.getPassword());
        loginUserE.setSalt(loginUserDO.getSalt());
        loginUserE.setEncryptionAlgorithmV(EnumUtil.getEnumByName(EncryptionAlgorithmV.class,loginUserDO.getEncryptionAlgorithm().getValue() ));
        loginUserE.setId(loginUserDO.getId());
        loginUserE.setAvailable(loginUserDO.getAvailable());
        loginUserE.setUserId(loginUserDO.getUserId());
        final TenantIdV tenantIdV = new TenantIdV();
        tenantIdV.setTenantId(loginUserDO.getTenantId());
        loginUserE.setTenantIdV(tenantIdV);
        return loginUserE;
    }

    public LoginUserE convert2Entity(AddLoginUserCmd loginUserDO) {
        final LoginUserE loginUserE = new LoginUserE();
        loginUserE.setUsername(loginUserDO.getUsername());
        loginUserE.setPassword(loginUserDO.getPassword());
        loginUserE.setEncryptionAlgorithmV(EnumUtil.getEnumByName(EncryptionAlgorithmV.class,loginUserDO.getEncryptionAlgorithm().getValue() ));
        loginUserE.setUserId(loginUserDO.getUserId());
        final TenantIdV tenantIdV = new TenantIdV();
        tenantIdV.setTenantId(loginUserDO.getTenantId());
        loginUserE.setTenantIdV(tenantIdV);
        return loginUserE;
    }

    public LoginUserDO convert2DO(LoginUserE loginUserE) {
        LoginUserDO loginUserDO = new LoginUserDO();
        loginUserDO.setUsername(loginUserE.getUsername());
        loginUserDO.setPassword(loginUserE.getPassword());
        loginUserDO.setSalt(loginUserE.getSalt());
        loginUserDO.setEncryptionAlgorithm(EnumUtil.getEnumByName(EncryptionAlgorithm.class, loginUserE.getEncryptionAlgorithmV().getValue()));
        loginUserDO.setUserId(loginUserE.getUserId());
        loginUserDO.setTenantId(loginUserE.getTenantIdV().getTenantId());

        return loginUserDO;

    }

}
