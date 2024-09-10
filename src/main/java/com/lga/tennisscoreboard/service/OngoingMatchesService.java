package com.lga.tennisscoreboard.service;

import com.lga.tennisscoreboard.dto.MatchDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static final Map<UUID, MatchDto> MATCHES = new HashMap();

    public UUID addMatch(MatchDto matchDto) {
        UUID uuid = UUID.randomUUID();
        MATCHES.put(uuid, matchDto);
        return uuid;
    }

    public MatchDto getMatch(UUID uuid){
        return MATCHES.get(uuid);
    }
}
