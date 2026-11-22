package com.lefredy.lestounes.controller;

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
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@WebMvcTest(UserController.class)
@Import(WebConfiguration.class)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;
    @MockitoBean
    MongoUserRepository mongoUserRepository;
    @MockitoBean
    JwtUtil jwtUtil;

    @Test
    void getAll_returns200AndAllUsers() throws Exception {
        User user = new User();
        when(userService.getUsers()).thenReturn(List.of(user));
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(userService).getUsers();
    }
    @ParameterizedTest
    @ValueSource(strings = {"uwu", "fred", "ced", "tahn", "roisbrule"})
    void getOne_returns200AndOneUsers(String arg) throws Exception {
        User user = new User(arg,arg);
        when(userService.getUserById(any(UUID.class))).thenReturn(user);
        mockMvc.perform(get("/api/v1/users/" + UUID.randomUUID()))
                .andExpect(status().isOk()).andExpect(jsonPath("$.username").value(arg));
        verify(userService).getUserById(any(UUID.class));
    }
    @Test
    void refreshLogin_returns200AndNewJwtToken() throws Exception {
        when(jwtUtil.generateToken("wow")).thenReturn("new token");
        when(jwtUtil.extractUsername("old token")).thenReturn("wow");
        when(userService.getByUsername("wow")).thenReturn(new User("wow", "wow"));
        mockMvc.perform(get("/api/v1/users/auth/refresh-login").header("Authorization", "Bearer old token"))
                .andExpect(status().isOk());
        verify(jwtUtil).generateToken(any());
    }

    @Test
    void signUp_returns200AndReturnNewUser() throws Exception {
        when(userService.signUp(any())).thenReturn(new User("dfsfsaefgaergregerfaregergae","efgdfgdfgewfewfwragaefa"));
        mockMvc.perform(post("/api/v1/users/auth/public/sign-up").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"password\": \"efgdfgdfgewfewfwragaefa\",\n" +
                                "    \"username\": \"dfsfsaefgaergregerfaregergae\"\n" +
                                "}\n"
                ))
                .andExpect(status().isCreated());
        verify(userService).signUp(any());
    }


    @Test
    void login_returns200AndNewJwtToken() throws Exception {
        when(userService.login(any())).thenReturn(anyString());
        mockMvc.perform(post("/api/v1/users/auth/public/login").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                        "    \"password\": \"efgdfgdfgewfewfwragaefa\",\n" +
                        "    \"username\": \"dfsfsaefgaergregerfaregergae\"\n" +
                        "}\n"
                ))
                .andExpect(status().isOk());
        verify(userService).login(any());
    }
    @Test
    void grantOne_returns200AndNewJwtToken() throws Exception {
        when(userService.grantAdmin(any())).thenReturn(new User("dfsfsaefgaergregerfaregergae","efgdfgdfgewfewfwragaefa"));
        mockMvc.perform(patch("/api/v1/users/auth/grant/d6aba72e-43a2-46d9-b110-39a0c633cd31"))
                .andExpect(status().isOk());
        verify(userService).grantAdmin(any());
    }

}
