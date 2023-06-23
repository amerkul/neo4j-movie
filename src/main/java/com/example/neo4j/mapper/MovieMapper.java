package com.example.neo4j.mapper;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.mutation.input.MovieInput;
import com.example.neo4j.mutation.update.MovieUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {PersonMapper.class, ReviewMapper.class, RoleMapper.class})
public interface MovieMapper {

    Movie toEntity(MovieInput input);
    Movie toEntity(MovieUpdate update);

}
