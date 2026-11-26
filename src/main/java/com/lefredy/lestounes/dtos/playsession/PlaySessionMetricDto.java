package com.lefredy.lestounes.dtos.playsession;

import com.lefredy.lestounes.constants.MediaStartAction;
import com.lefredy.lestounes.constants.MediaStopAction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public class PlaySessionMetricDto {
    @NotNull
    private UUID trackId;
    @NotNull
    private UUID userId;
    @NotNull
    @PositiveOrZero
    private Long startMs;
    @NotNull
    @PositiveOrZero
    private Long endMs;
    @NotNull
    private MediaStartAction startAction;
    @NotNull
    private MediaStopAction stopAction;
    @NotNull
    @PositiveOrZero
    private Long timeStamp;

    public UUID getTrackId() {
        return trackId;
    }

    public void setTrackId(UUID trackId) {
        this.trackId = trackId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Long getStartMs() {
        return startMs;
    }

    public void setStartMs(Long startMs) {
        this.startMs = startMs;
    }

    public Long getEndMs() {
        return endMs;
    }

    public void setEndMs(Long endMs) {
        this.endMs = endMs;
    }

    public MediaStartAction getStartAction() {
        return startAction;
    }

    public void setStartAction(MediaStartAction startAction) {
        this.startAction = startAction;
    }

    public MediaStopAction getStopAction() {
        return stopAction;
    }

    public void setStopAction(MediaStopAction stopAction) {
        this.stopAction = stopAction;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}