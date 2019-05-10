package com.oneday.core.web;//package one.day;

import com.oneday.core.exception.UnLoginException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author zzf
 */
public class ProjectUtil {
    /**
     * 获取公司ID ，其值在登录成功的时候存放进session
     *
     * @return
     */
    public static long getParentId() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return isLogin(user.getParentId());
        return 1L;
    }


    public static long getLoginId() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return isLogin(user.getId());
        return 1L;
    }

    public static String getUserNumber() {
        return (String) Optional.ofNullable(getSessionValue("userNumber")).orElseThrow(UnLoginException::new);
    }

    public static String getTenantCode() {
        // TODO: 2019/1/8 anquan
        return (String) Optional.ofNullable(getSessionValue("tenantCode")).orElseThrow(UnLoginException::new);
    }

    private static Long isLogin(Long id) {
        return Optional.ofNullable(id).orElseThrow(UnLoginException::new);
    }

    public static void setSession(String key, Object value) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    public static Object getSessionValue(String key) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    public static void main(String[] args) {
//        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
//                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//// 登录名
//        System.out.println("Username:"
//                + securityContextImpl.getAuthentication().getName());
//// 登录密码，未加密的
//        System.out.println("Credentials:"
//                + securityContextImpl.getAuthentication().getCredentials());
//        WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl
//                .getAuthentication().getDetails();
//// 获得访问地址
//        System.out.println("RemoteAddress" + details.getRemoteAddress());
//// 获得sessionid
//        System.out.println("SessionId" + details.getSessionId());
//// 获得当前用户所拥有的权限
//        List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl
//                .getAuthentication().getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            System.out.println("Authority" + grantedAuthority.getAuthority());
//        }

    }
}
