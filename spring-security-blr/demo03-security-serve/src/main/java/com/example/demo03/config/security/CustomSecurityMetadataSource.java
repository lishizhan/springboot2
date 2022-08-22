package com.example.demo03.config.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;

/**
 * @Author : lishizhan
 * @Date : 2022/8/14/0014
 * @email : 1575234570@qq.com
 * @Description : 动态权限
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    /**
     * 自定义动态资源权限的元数据
     * @param object the object being secured
     *
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
