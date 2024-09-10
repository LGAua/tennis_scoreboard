package com.lga.tennisscoreboard.mapper;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(source = "playerOne", target = "firstPlayer")
    @Mapping(source = "playerTwo", target = "secondPlayer")
    @Mapping(source = "winner", target = "winner")
    Match mapToMatch(MatchDto matchDto);

    @Mapping(source = "firstPlayer", target = "playerOne")
    @Mapping(source = "secondPlayer", target = "playerTwo")
    @Mapping(source = "winner", target = "winner")
    MatchDto mapToMatchDto(Match match);
}
