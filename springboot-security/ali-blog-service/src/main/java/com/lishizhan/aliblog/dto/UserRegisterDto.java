package com.lishizhan.aliblog.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.lishizhan.aliblog.common.RegexPatterns;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author : Alishiz
 * @Date : 2022/7/28/0028
 * @email : 1575234570@qq.com
 * @Description :
 */
@Data
public class UserRegisterDto {
    /**
     * 用户手机号
     */
    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = RegexPatterns.PHONE_REGEX,message = "不是一个合法的手机号")
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 18,message = "密码长度必须在6~18位")
    private String password;

    /**
     * 用户邮箱
     */
    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 用户昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 验证码
     * */
    @NotBlank(message = "验证码不能为空")
    private String code;
}
