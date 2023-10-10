package org.newmicro.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AppUser implements UserDetails {

    private int appUserId;
    private String username;
    private String password; // could remove if we use OAuth
    private boolean enabled;
    private Collection<GrantedAuthority> authorities = List.of();

    public AppUser() {}

    public AppUser(int appUserId, String username, String password, boolean enabled, List<String> roles) {
        this.appUserId = appUserId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = convertRolesToAuthorities(roles);
    }

    private static Collection<GrantedAuthority> convertRolesToAuthorities(List<String> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r))
                .collect(Collectors.toList());
    }


    @Override // change to Collection<GrantedAuthority>?
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public int getAppUserId() {return appUserId;}

    public void setAppUserId() {this.appUserId = appUserId;}

    public void setPassword() {this.password = password;}
}
