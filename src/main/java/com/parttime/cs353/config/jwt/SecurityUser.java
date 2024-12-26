package com.parttime.cs353.config.jwt;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parttime.cs353.pojo.data.UserDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-26 14:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {
    private Integer uid;
    private String phone;
    private String password;
    private int status;
    private List<GrantedAuthority> authorities=new ArrayList<>();

    public SecurityUser(int status){this.status=status;}
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return sysRoles;
        return authorities;
    }

    @Override
    public String getUsername() {
        return phone;
    }


    /**
     * is account expired
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return status != 1;
    }

    /**
     * is account locked
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return status != 2;
    }

    /**
     * is credit expired
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return status != 3;
    }

    /**
     * is account blocked
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return status != 4;
    }
}