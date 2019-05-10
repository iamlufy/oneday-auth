package com.oneday.auth.authentication.infrastructure.adapt;

import com.oneday.BaseRepository;
import com.oneday.BaseServiceImpl;
import com.oneday.auth.authentication.domain.entity.LoginUserE;
import com.oneday.auth.authentication.domain.port.LoginUserRepositoryPort;
import com.oneday.auth.authentication.infrastructure.converter.LoginUserConverter;
import com.oneday.auth.authentication.infrastructure.dataobject.LoginUserDO;
import com.oneday.auth.authentication.infrastructure.tunnel.LoginUserTunnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 登录用户仓储端口实现类
 *
 * @author zhuangzf
 * @date 2019/5/6 16:09
 */
@Service
public class LoginUserRepositoryAdapt  implements LoginUserRepositoryPort {
    private final LoginUserTunnel loginUserTunnel;
    private final LoginUserConverter loginUserConverter;

    @Autowired
    public LoginUserRepositoryAdapt(LoginUserConverter loginUserConverter, LoginUserTunnel loginUserTunnel) {
        this.loginUserConverter = loginUserConverter;
        this.loginUserTunnel = loginUserTunnel;
    }

    @Override
    public Optional<LoginUserE> findByUsername(String username) {
        Optional<LoginUserDO> byUsername = loginUserTunnel.findByUsername(username);
        return byUsername.map(loginUserConverter::convert2Entity);
    }

    @Override
    public void add(LoginUserE loginUserE) {
        loginUserTunnel.add(loginUserConverter.convert2DO(loginUserE));
    }
}
