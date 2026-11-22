package com.lefredy.lestounes.dtos.user;

import jakarta.validation.constraints.Size;

import java.util.Optional;

public class UserUpdateDto {
    @Size(min = 3,max = 150)
    Optional<String> password = Optional.empty();
    @Size(min = 3,max = 150)
    Optional<String> username = Optional.empty();
    public Optional<String> getPassword() {
        return password;
    }

    public Optional<String> getUsername() {
        return username;
    }

    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }
}
