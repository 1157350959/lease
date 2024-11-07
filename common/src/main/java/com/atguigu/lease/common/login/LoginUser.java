package com.atguigu.lease.common.login;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName: LoginUser
 * Package: com.atguigu.lease.common.login
 * Description:
 *
 * @Author HL
 * @Create 11/6/2024 10:15 PM
 * @Version:
 */
@Data
@AllArgsConstructor
public class LoginUser {
    private Long userId;
    private String username;
}
