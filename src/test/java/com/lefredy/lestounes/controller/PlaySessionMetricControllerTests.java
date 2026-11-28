package com.lefredy.lestounes.controller;

import com.lefredy.lestounes.config.WebConfiguration;
import com.lefredy.lestounes.service.PlaySessionMetricService;
import com.lefredy.lestounes.service.UserService;
import com.lefredy.lestounes.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaySessionMetricController.class)
@Import(WebConfiguration.class)
public class PlaySessionMetricControllerTests {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    JwtUtil jwtUtil;
    @MockitoBean
    UserService userService;
    @MockitoBean
    PlaySessionMetricService playSessionMetricService;

    @Test
    void createPlaySessionMetric_returns201() throws Exception {
        mockMvc.perform(post("/api/v1/metrics/PlaySession")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "trackId": "d6aba72e-43a2-46d9-b110-39a0c633cd31",
                                    "trackTitle": "Titre",
                                    "userId": "1b4e28ba-2fa1-11d2-883f-0016d3cca427",
                                    "startMs": 0,
                                    "endMs": 1000,
                                    "startAction": "START",
                                    "stopAction": "END",
                                    "timeStamp": 1764100000000
                                }
                                """))
                .andExpect(status().isCreated());
        verify(playSessionMetricService).createPlaySessionMetric(any());
    }

    @Test
    void createPlaySessionMetric_missingField_returns400() throws Exception {
        mockMvc.perform(post("/api/v1/metrics/PlaySession")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "trackId": "d6aba72e-43a2-46d9-b110-39a0c633cd31",
                                    "startMs": 0
                                }
                                """))
                .andExpect(status().isBadRequest());
        verify(playSessionMetricService, never()).createPlaySessionMetric(any());
    }
}