package com.lefredy.lestounes.controller;

import com.lefredy.lestounes.config.WebConfiguration;
import com.lefredy.lestounes.repository.TracksRepository;
import com.lefredy.lestounes.service.TracksService;
import com.lefredy.lestounes.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
@Import(WebConfiguration.class)
public class TracksControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    JwtUtil jwtUtil;
    @MockitoBean
    TracksService tracksService;
    @MockitoBean
    TracksRepository tracksRepository;

}
