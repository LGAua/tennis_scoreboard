package com.lga.tennisscoreboard.mapper;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {

    public static final MatchMapper INSTANCE =Mappers.getMapper(MatchMapper.class);

    Match mapToMatch(MatchDto matchDto);

    MatchDto mapToMatchDto(Match match);
}
