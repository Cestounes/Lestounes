package com.lefredy.lestounes.dtos.playsession;

import com.lefredy.lestounes.constants.MediaStartAction;
import com.lefredy.lestounes.constants.MediaStopAction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;
import java.util.UUID;

public record PlaySessionMetricDto(
        @NotNull UUID trackId,
        @NotNull UUID userId,
        @NotNull @PositiveOrZero Long startMs,
        @NotNull @PositiveOrZero Long endMs,
        @NotNull MediaStartAction startAction,
        @NotNull MediaStopAction stopAction,
        @NotNull Date timeStamp
) {
}