package com.example.security07.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.security07.entity.Role;
import com.example.security07.entity.User;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author : Alishiz
 * @Date : 2022/7/29/0029
 * @email : 1575234570@qq.com
 * @Description :
 */
@Data
@Slf4j
public class UserDto implements UserDetails {

    private User user;

    private List<Role> roles = new ArrayList<>();

    @JSONField(serialize = false) //不进行序列化
    private Set<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //因为每次调用都要进行转换所以将authorities定义为成员变量
        //Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        log.info("authorities----->{}", authorities);
        log.info("roles----->{}", roles);
        if (authorities != null) return authorities;

        authorities = roles.stream()
                .map((Role role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        log.info("authorities end----->{}", authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
