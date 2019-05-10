package com.oneday.auth.authentication.domain.service;

import com.oneday.auth.authentication.client.dto.AddLoginUserCmd;
import com.oneday.auth.authentication.client.dto.LoginCmd;

/**
 * 登录服务
 *
 * @author zhuangzf
 * @date 2019/3/29 10:36
 */
public interface LoginService {
    /**
     * 执行登录服务
     *
     * @param loginCmd
     */
    String login(LoginCmd loginCmd);

    /**
     * 增加登录用户
     *
     * @param addLoginUserCmd
     * @return
     */
    void addLoginUser(AddLoginUserCmd addLoginUserCmd);
}
