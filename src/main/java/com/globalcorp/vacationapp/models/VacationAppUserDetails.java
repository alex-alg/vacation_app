package com.globalcorp.vacationapp.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VacationAppUserDetails implements UserDetails {
    private Long id;
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
    private int annualLeaveDays;
    private List<Leave> leaves;

    public VacationAppUserDetails() {
    }

    public VacationAppUserDetails(User user){
        this.id = user.getId();
        this.userName = getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        this.annualLeaveDays = user.getAnnualLeaveDays();
        this.leaves = user.getLeaves();
    }

    public Long getId() {
        return id;
    }

    public int getAnnualLeaveDays() {
        return annualLeaveDays;
    }

    public List<Leave> getLeaves() {
        return leaves;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return active;
    }
}
