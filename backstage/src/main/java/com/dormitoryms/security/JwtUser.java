package com.dormitoryms.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 安全用户模型
 *
 * @author hackyo
 * Created on 2018/3/18 9:20.
 */
@AllArgsConstructor
public class JwtUser implements UserDetails {

    @Getter
    @JsonIgnore
    private String id;

    @Getter
    private String username;

    @Getter
    @JsonIgnore
    private String password;

    @Getter
    @JsonIgnore
    private String lastLoginIp;

    @Getter
    @JsonIgnore
    private Date lastPasswordResetTime;

    @Getter
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
