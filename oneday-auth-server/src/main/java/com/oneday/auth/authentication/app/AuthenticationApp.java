package com.oneday.auth.authentication.app;

import com.oneday.auth.authentication.client.dto.AddLoginUserCmd;
import com.oneday.auth.authentication.client.dto.LoginCmd;
import com.oneday.auth.authentication.domain.service.LoginService;
import com.oneday.auth.authentication.infrastructure.tunnel.LoginUserTunnel;
import com.oneday.core.exception.UnLoginException;
import com.oneday.core.global.LoginCode;
import com.oneday.core.web.ProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 认证相关功能应用层
 *
 * @author zhuangzf
 * @date 2019/3/28 14:56
 */
@Component
public class AuthenticationApp {

    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginUserTunnel loginUserTunnel;
    /**
     * 登录
     *
     * @param loginCmd
     */
    public void login(LoginCmd loginCmd) {
        String userId = loginService.login(loginCmd);
        //session中存放userId已证明登录
        //由于领域层主要负责登录，或者校验密码，登录成功之后的登录态设定不关心，交由应用层负责
        ProjectUtil.setSession("userId", userId);
    }

    public void checkLogin() {
        Long userId = (Long) ProjectUtil.getSessionValue("userId");
        Optional.ofNullable(userId).orElseThrow(() -> new UnLoginException(LoginCode.UN_LOGIN));
    }

    public void addLoginUser(AddLoginUserCmd addLoginUserCmd) {
        loginService.addLoginUser(addLoginUserCmd);
    }
}
