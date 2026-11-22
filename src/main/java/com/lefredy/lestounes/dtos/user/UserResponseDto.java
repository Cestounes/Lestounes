package com.lefredy.lestounes.dtos.user;

import com.lefredy.lestounes.constants.eRoles;
import com.lefredy.lestounes.schemas.User;

import java.util.EnumSet;

public class UserResponseDto{
    String username;
    EnumSet<eRoles> roles;

    public EnumSet<eRoles> getRoles() {
        return roles;
    }

    public void setRoles(EnumSet<eRoles> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.roles = user.getRoles();
    }

}
