package com.lefredy.lestounes.controller;

import com.lefredy.lestounes.config.WebConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;

@WebMvcTest(PlaylistsController.class)
@Import(WebConfiguration.class)
public class PlaylistsControllerTest {

}
