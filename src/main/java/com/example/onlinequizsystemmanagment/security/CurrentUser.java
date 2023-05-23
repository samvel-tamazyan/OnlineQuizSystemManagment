package com.example.onlinequizsystemmanagment.security;

import com.example.onlinequizsystemmanagment.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(),true,true,
                true,true, getAuthorities(user));
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream()
                .map(role->role.name().toUpperCase()).toArray(String[]::new);

        return AuthorityUtils.createAuthorityList(userRoles);
    }

}