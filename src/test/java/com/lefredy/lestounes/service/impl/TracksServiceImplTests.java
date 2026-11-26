package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.repository.TracksRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TracksServiceImplTests {

    @Mock
    private TracksRepository tracksRepository;
    @InjectMocks
    private TracksServiceImpl tracksServiceImpl;
}
