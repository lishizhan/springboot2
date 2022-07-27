package com.example.security03.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable, UserDetails {
    /**
     * id
     */
    private Long id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 账户是否启用
     */
    private Integer enabled;

    /**
     * 账户是否过期
     */
    private Integer accountNonExpired;

    /**
     * 账户是否被锁
     */
    private Integer accountNonLocked;

    /**
     * 密码是否过期
     */
    private Integer credentialsNonExpired;

    /**
     * 关系属性，用于存储用户的所有权限学习
     * */
    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



    /**
     * 返回权限信息
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        this.roles.forEach(role -> {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
        });
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired==1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired==1;
    }

    @Override
    public boolean isEnabled() {
        return enabled==1;
    }
}