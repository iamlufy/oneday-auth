package com.oneday.auth.authentication.domain.service.impl;

import com.oneday.auth.authentication.client.dto.AddLoginUserCmd;
import com.oneday.auth.authentication.client.dto.LoginCmd;
import com.oneday.auth.authentication.domain.port.LoginUserRepositoryPort;
import com.oneday.auth.authentication.infrastructure.converter.LoginUserConverter;
import com.oneday.auth.authentication.domain.entity.LoginUserE;
import com.oneday.auth.authentication.domain.service.LoginService;
import com.oneday.core.exception.BaseException;
import com.oneday.core.global.GlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 登录服务
 *
 * @author zhuangzf
 * @date 2019/3/29 10:38
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final LoginUserRepositoryPort loginUserRepositoryPort;
    private final LoginUserConverter loginUserConverter;

    @Autowired
    public LoginServiceImpl(LoginUserRepositoryPort loginUserRepositoryPort, LoginUserConverter loginUserConverter) {
        this.loginUserRepositoryPort = loginUserRepositoryPort;
        this.loginUserConverter = loginUserConverter;
    }

    @Override
    public String login(LoginCmd loginCmd) {
        Optional<LoginUserE> optionalLoginUserE = loginUserRepositoryPort.findByUsername(loginCmd.getUsername());
        optionalLoginUserE.orElseThrow(() -> new BaseException(GlobalEnum.NON_EXIST));
        LoginUserE loginUserE = optionalLoginUserE.get();
        loginUserE.login(loginCmd.getPassword());

        //todo 登录成功，异步通知观察者

        return loginUserE.getUserId();
    }

    @Override
    public void addLoginUser(AddLoginUserCmd addLoginUserCmd) {
        LoginUserE loginUserE = loginUserConverter.convert2Entity(addLoginUserCmd);
        loginUserE.encryptPassword();
        loginUserRepositoryPort.add(loginUserE);
    }
}
