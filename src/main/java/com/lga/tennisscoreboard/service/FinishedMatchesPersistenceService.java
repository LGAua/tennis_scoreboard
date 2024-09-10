package com.lga.tennisscoreboard.service;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Match;
import com.lga.tennisscoreboard.mapper.MatchMapper;
import com.lga.tennisscoreboard.repository.DaoCrud;
import com.lga.tennisscoreboard.repository.MatchRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class FinishedMatchesPersistenceService {

    private final DaoCrud matchRepository = new MatchRepository();

    public void saveMatch(MatchDto matchDto) {
        Match match = MatchMapper.INSTANCE.mapToMatch(matchDto);
        matchRepository.save(match);
    }
}
