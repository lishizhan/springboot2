package com.example.security02.dto;

import lombok.Data;

/**
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-22 16:17:58
 */
@Data
public class UserLoginVo {

    /**
     * 手机号,邮箱
     */
    private String phone_or_email;

    /**
     * 密码
     */
    private String password;


}
