package com.lefredy.lestounes.schemas;


import com.lefredy.lestounes.dtos.user.PlaylistsDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistsTest {

    @Test
    void constructor_copiesEveryFieldFromTheDto() {
        PlaylistsDto dto = new PlaylistsDto();
        dto.setName("Glad");
        List<UUID> list = new ArrayList<>();
        list.add(UUID.randomUUID());
        list.add(UUID.randomUUID());
        list.add(UUID.randomUUID());
        dto.setTracks(list);

        Playlists playlists = new Playlists(dto);

        assertNotNull(playlists.getId());
        assertEquals("Glad", playlists.getName());
        assertEquals(list.toArray(), playlists.getTracks().toArray());
        assertFalse(playlists.getDeleted());

    }
}
