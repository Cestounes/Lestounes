package com.lefredy.lestounes.schemas;

import com.lefredy.lestounes.constants.eRoles;
import com.lefredy.lestounes.dtos.user.UserDto;
import com.lefredy.lestounes.dtos.user.UserUpdateDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Document
public class User implements UserDetails {
    @Id
    private UUID id;
    private String password;
    @Indexed(unique = true)
    private String username;
    private EnumSet<eRoles> roles = EnumSet.noneOf(eRoles.class);

    public User() {

    }

    public User( String username, String password){
        id = UUID.randomUUID();
        setPassword(password);
        setUsername(username);
        roles.add(eRoles.USER);
    }
    public User(UserDto userDto, PasswordEncoder passwordEncoder){
        this(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()));
    }
    public void updateUser(UserUpdateDto userUpdateDto, PasswordEncoder passwordEncoder){
    userUpdateDto.getPassword().ifPresent(value -> this.setPassword(passwordEncoder.encode(value)));
    userUpdateDto.getUsername().ifPresent(this::setUsername);
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EnumSet<eRoles> getRoles() {
        return roles;
    }

    public void setRoles(EnumSet<eRoles> roles) {
        this.roles = roles;
    }
    public void addRole(eRoles role){
        roles.add(role);
    }
}
