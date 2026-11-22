package com.lefredy.lestounes.service;

import com.lefredy.lestounes.dtos.user.UserDto;
import com.lefredy.lestounes.dtos.user.UserUpdateDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.schemas.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getUsers();

    User getUserById(UUID id) throws DocumentNotFoundException;
    User getByUsername(String username) throws DocumentNotFoundException;
    User signUp(UserDto userDto);

    String login (UserDto userDto);

    User updateUser(UUID id,UserUpdateDto userUpdateDto) throws DocumentNotFoundException;

    User grantAdmin(UUID id) throws DocumentNotFoundException;
}