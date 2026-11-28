package com.lefredy.lestounes.dtos.playsession;

import com.lefredy.lestounes.constants.eMediaStartAction;
import com.lefredy.lestounes.constants.eMediaStopAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;
import java.util.UUID;

public record PlaySessionMetricDto(
        @NotNull UUID trackId,
        @NotBlank String trackTitle,
        @NotNull UUID userId,
        @NotNull @PositiveOrZero Long startMs,
        @NotNull @PositiveOrZero Long endMs,
        @NotNull eMediaStartAction startAction,
        @NotNull eMediaStopAction stopAction,
        @NotNull Date timeStamp
) {
}