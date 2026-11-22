package com.lefredy.lestounes.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    @NotBlank
    @Size(min = 3,max = 150)
    String password;
    @NotBlank
    @Size(min = 3,max = 150)
    String username;
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
