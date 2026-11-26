package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.dtos.user.TracksFilterDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.repository.TracksRepository;
import com.lefredy.lestounes.schemas.Tracks;
import com.lefredy.lestounes.service.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class TracksServiceImpl implements TracksService {

    @Autowired
    private TracksRepository tracksRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${MUSIC_ROOT:./music}")
    private String musicRoot;

    @Override
    public List<Tracks> getTracksFiltered(TracksFilterDto tracksFilterDto) {
        Query query = new Query();

        addContainsFilter(query, "title", tracksFilterDto.getTitle());
        addContainsFilter(query, "artists", tracksFilterDto.getArtist());
        addContainsFilter(query, "album", tracksFilterDto.getAlbum());
        addContainsFilter(query, "albumArtist", tracksFilterDto.getAlbumArtist());

        if (tracksFilterDto.getGenre() != null) {
            query.addCriteria(Criteria.where("genres").is(tracksFilterDto.getGenre().toLowerCase(Locale.ROOT)));
        }
        if (tracksFilterDto.getUpdatedAt() != null) {
            query.addCriteria(Criteria.where("updatedAt").gt(tracksFilterDto.getUpdatedAt()));
        }

        query.with(PageRequest.of(tracksFilterDto.getPage(), tracksFilterDto.getPageNbLimit(), Sort.by("title")));

        return mongoTemplate.find(query, Tracks.class);
    }

    private void addContainsFilter(Query query, String field, String value){
        if (value != null && !value.isBlank()) {
            query.addCriteria(Criteria.where(field).regex(Pattern.quote(value),"i"));
        }
    }
    @Override
    public Tracks getTrackById(UUID id) throws DocumentNotFoundException {
        return tracksRepository.findById(id).orElseThrow(DocumentNotFoundException::new);
    }
    @Override
    public Resource getTrackFileById(UUID id) {
        Tracks track = getTrackById(id);

        Path filePath = Paths.get(musicRoot).resolve(track.getPath()).normalize();
        Resource resource = new FileSystemResource(filePath);

        if(!resource.exists()) {
            throw new DocumentNotFoundException();
        }

        return resource;
    }
}
