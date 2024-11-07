package com.atguigu.lease.common.login;

/**
 * ClassName: LoginUserHolder
 * Package: com.atguigu.lease.common.login
 * Description:
 *
 * @Author HL
 * @Create 11/6/2024 10:15 PM
 * @Version:
 */
public class LoginUserHolder {
    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static void setLoginUser(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }

    public static void removeLoginUser() {
        threadLocal.remove();
    }
}
