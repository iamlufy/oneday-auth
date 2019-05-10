package com.oneday.auth.authentication;

import com.oneday.auth.authentication.app.AuthenticationApp;
import com.oneday.auth.authentication.client.dto.AddLoginUserCmd;
import com.oneday.auth.authentication.client.dto.LoginCmd;
import com.oneday.core.web.OdResponse;
import com.oneday.core.web.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *  登录相关接口
 *
 * @author zhuangzf
 * @date 2019/3/29 15:24
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationApp authenticationApp;

    /**
     * cookie-session模式下的登录
     *
     * @param request
     * @return
     */
    @PostMapping("/api/login")
    public HttpEntity<OdResponse> login(@RequestBody @Valid Request<LoginCmd> request) {
        authenticationApp.login(request.getParams());
        return OdResponse.ofSuccess();
    }

    /**
     * 登录检查
     *
     * @return
     */
    @PostMapping("/api/login/check")
    public HttpEntity<OdResponse> checkLogin() {
        authenticationApp.checkLogin();
        return OdResponse.ofSuccess();
    }

    /**
     * 增加登录用户
     *
     * @param request
     * @return
     */
    @PutMapping("/api/auth/login/user")
    public HttpEntity<OdResponse> addLoginUser(@RequestBody @Valid Request<AddLoginUserCmd> request) {
        authenticationApp.addLoginUser(request.getParams());
        return OdResponse.ofSuccess();
    }
}
