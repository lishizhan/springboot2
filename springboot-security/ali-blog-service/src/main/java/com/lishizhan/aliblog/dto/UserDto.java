package com.lishizhan.aliblog.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.lishizhan.aliblog.entity.Role;
import com.lishizhan.aliblog.service.UserService;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

/**
 * @Author : Alishiz
 * @Date : 2022/7/28/0028
 * @email : 1575234570@qq.com
 * @Description :
 */
@Data
public class UserDto implements UserDetails {
    /**
     * 用户唯一ID
     */
    private Long id;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户是否过期
     */
    private Boolean accountNonExpired;

    /**
     * 账户是否被锁
     */
    private Boolean accountNonLocked;

    /**
     * 密码是否过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 账户是否启用
     */
    private Boolean enabled;


    /**
     * 该用户的权限
     * */
    private List<Role> roles =new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> roleSet = new HashSet<>();
        roles.forEach(role -> {
            roleSet.add(new SimpleGrantedAuthority(role.getName()));
        });
        return roleSet;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
