package com.example.demo03.entity.dto;

import com.example.demo03.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Author : lishizhan
 * @Date : 2022/8/13/0013
 * @email : 1575234570@qq.com
 * @Description :
 */
@Data
public class UserDto implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Optional.ofNullable(user.getRoles()).ifPresent(roles -> user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPwd();
    }

    @Override
    public String getUsername() {
        return user.getUname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountnonexpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountnonlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsnonexpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
