package com.oneday.auth.authentication.domain.port;

import com.oneday.auth.authentication.domain.entity.LoginUserE;
import com.oneday.core.base.IBaseService;

import java.util.Optional;

/**
 * 登录用户仓储端口逻辑定义类
 *
 * @author zhuangzf
 * @date 2019/5/6 16:02
 */
public interface LoginUserRepositoryPort{
    /**
     * 根据用户名查找登录用户
     *
     * @param username
     * @return
     * @author zhuangzf
     */
    Optional<LoginUserE> findByUsername(String username);

    /**
     * 增加登录用户
     *
     * @param loginUserE
     * @author zhuangzf
     */
    void add(LoginUserE loginUserE);
}
