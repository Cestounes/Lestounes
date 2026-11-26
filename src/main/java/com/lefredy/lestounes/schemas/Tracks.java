package com.lefredy.lestounes.schemas;

import com.lefredy.lestounes.dtos.user.TracksDto;
import com.lefredy.lestounes.dtos.user.TracksFilterDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document
public class Tracks {
    @Id
    private UUID id;
    private String path;
    @Indexed(unique = true)
    private String title;
    private Long length;
    private List<String> artists;
    private String album;
    private String albumArtist;
    private String composer;
    private Date date;
    private List<String> genres;
    private Integer trackNumber = 0;
    private Integer discNumber = 0;
    private String comment;
    private Date updatedAt;
    private Boolean deleted;

    public Tracks(TracksDto tracksDto) {
        this.id = UUID.randomUUID();
        this.path = tracksDto.getPath();
        this.title = tracksDto.getTitle();
        this.length = tracksDto.getLength();
        setGenres(tracksDto.getGenres());
        setTrackNumber(tracksDto.getTrackNumber());
        setDiscNumber(tracksDto.getDiscNumber());
        this.comment = tracksDto.getComment();
        this.updatedAt = tracksDto.getUpdatedAt();
        this.deleted = false;
    }

    public Tracks() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
