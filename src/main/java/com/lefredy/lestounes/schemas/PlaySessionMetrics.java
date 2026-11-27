package com.lefredy.lestounes.schemas;

import com.lefredy.lestounes.constants.MediaStartAction;
import com.lefredy.lestounes.constants.MediaStopAction;
import com.lefredy.lestounes.dtos.playsession.PlaySessionMetricDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class PlaySessionMetrics {
    @Id
    private UUID id;
    private UUID trackId;
    private String trackTitle;
    private UUID userId;
    private Long startMs;
    private Long endMs;
    private MediaStartAction startAction;
    private MediaStopAction stopAction;
    private Date timeStamp;

    public PlaySessionMetrics() {

    }

    public PlaySessionMetrics(UUID trackId, String trackTitle, UUID userId, Long startMs, Long endMs,
            MediaStartAction startAction, MediaStopAction stopAction, Date timeStamp) {
        this.id = UUID.randomUUID();
        this.trackId = trackId;
        this.trackTitle = trackTitle;
        this.userId = userId;
        this.startMs = startMs;
        this.endMs = endMs;
        this.startAction = startAction;
        this.stopAction = stopAction;
        this.timeStamp = timeStamp;
    }

    public PlaySessionMetrics(PlaySessionMetricDto dto, String trackTitle) {
        this(dto.trackId(), trackTitle, dto.userId(), dto.startMs(), dto.endMs(),
                dto.startAction(), dto.stopAction(), dto.timeStamp());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTrackId() {
        return trackId;
    }

    public void setTrackId(UUID trackId) {
        this.trackId = trackId;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}