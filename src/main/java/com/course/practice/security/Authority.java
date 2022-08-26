package com.course.practice.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private String authority;

    public Authority(String authority) {
        authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
