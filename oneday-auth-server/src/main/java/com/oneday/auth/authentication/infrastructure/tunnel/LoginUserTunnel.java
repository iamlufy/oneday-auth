package com.oneday.auth.authentication.infrastructure.tunnel;

import com.oneday.auth.authentication.infrastructure.dataobject.LoginUserDO;
import com.oneday.core.base.IBaseService;

import java.util.Optional;

/**
 * @author zhuangzf
 * @date 2019/3/28 14:45
 */
public interface LoginUserTunnel extends IBaseService<LoginUserDO> {
    Optional<LoginUserDO> findByUsername(String username);
}
