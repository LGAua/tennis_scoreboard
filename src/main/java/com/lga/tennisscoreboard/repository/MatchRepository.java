package com.lga.tennisscoreboard.repository;

import com.lga.tennisscoreboard.entity.Match;

public class MatchRepository extends BaseRepository<Long, Match> {

    public MatchRepository() {
        super(Match.class);
    }
}
