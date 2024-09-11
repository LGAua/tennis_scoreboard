package com.lga.tennisscoreboard.service;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.dto.Page;
import com.lga.tennisscoreboard.entity.Match;
import com.lga.tennisscoreboard.mapper.MatchMapper;
import com.lga.tennisscoreboard.repository.MatchRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository = new MatchRepository();

    public void saveMatch(MatchDto matchDto) {
        Match match = MatchMapper.INSTANCE.mapToMatch(matchDto);
        matchRepository.save(match);
    }

    public Page<Match> getMatches(String filterByPlayerName, String pageStr) {
        int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);
        if (filterByPlayerName == null) {
            filterByPlayerName = "";
        }
        return matchRepository.getMatches(filterByPlayerName, page);
    }
}
