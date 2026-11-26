package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.constants.eRoles;
import com.lefredy.lestounes.dtos.user.UserDto;
import com.lefredy.lestounes.dtos.user.UserUpdateDto;
import com.lefredy.lestounes.exceptions.AuthentificationUnSuccessfulException;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.repository.MongoUserRepository;
import com.lefredy.lestounes.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.lefredy.lestounes.config.WebConfiguration;
import com.lefredy.lestounes.repository.MongoUserRepository;
import com.lefredy.lestounes.schemas.User;
import com.lefredy.lestounes.service.UserService;
import com.lefredy.lestounes.utils.JwtUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {
    @Mock
    private MongoUserRepository personRepository;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void getUsers_returnEmptyList() {
        when(personRepository.findAll()).thenReturn(List.of());
        userService.getUsers();
        verify(personRepository).findAll();
    }

    @Test
    void getUserById_returnOneUser() {
        User user = new User("wow","wow");
        when(personRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        assertEquals(userService.getUserById(UUID.randomUUID()), user);
        verify(personRepository).findById(any(UUID.class));
    }
    @Test
    void getUserById_throwsDocumentNotFound() {
        when(personRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(DocumentNotFoundException.class,() -> userService.getUserById(UUID.randomUUID()));
        verify(personRepository).findById(any(UUID.class));
    }

    @Test
    void getByUsername_returnOneUser() {
        User user = new User("wow","wow");
        when(personRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        assertEquals(userService.getByUsername("wow"), user);
        verify(personRepository).findByUsername(anyString());
    }
    @Test
    void getByUsername_throwsDocumentNotFound() {
        when(personRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(DocumentNotFoundException.class,() -> userService.getByUsername("wow"));
        verify(personRepository).findByUsername(anyString());
    }

    @Test
    void signUp_returnOneUser() {
        User user = new User("wow","wow");
        UserDto userDto = new UserDto();
        userDto.setPassword("wow");
        userDto.setUsername("wow");
        when(personRepository.save(any())).thenReturn(user);
        assertEquals(userService.signUp(userDto),user);
        verify(personRepository).save(any());
    }

    @Test
    void login_returnJwtToken() {
        User user = new User("wow","wow");
        UserDto userDto = new UserDto();
        userDto.setPassword("wow");
        userDto.setUsername("wow");
        when(personRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(),any())).thenReturn(true);
        when(jwtUtil.generateToken("wow")).thenReturn("token");
        assertEquals("token", userService.login(userDto));
    }
    @Test
    void login_returnAuthentificationError() {
        User user = new User("wow","wow");
        UserDto userDto = new UserDto();
        userDto.setPassword("wow");
        userDto.setUsername("wow");
        when(personRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(),any())).thenReturn(false);
        assertThrows(AuthentificationUnSuccessfulException.class,() -> userService.login(userDto));
    }

    @Test
    void updateUser_returnUpdatedPassword() {
        User user = new User("wow","wow");
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setPassword(Optional.of("wow"));
        when(personRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(any())).thenReturn("encodedpassword");
        when(personRepository.save(any())).thenReturn(user);
        assertEquals("encodedpassword", userService.updateUser(UUID.randomUUID(),userUpdateDto).getPassword());
    }

    @Test
    void grantAdmin_hasAdmin() {
        User user = new User("wow","wow");
        when(personRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(personRepository.save(any())).thenReturn(user);
        assertTrue(userService.grantAdmin(UUID.randomUUID()).getRoles().contains(eRoles.ADMIN));
    }
}
