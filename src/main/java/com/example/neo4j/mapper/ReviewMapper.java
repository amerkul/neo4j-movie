package com.example.neo4j.mapper;

import com.example.neo4j.domain.Review;
import com.example.neo4j.mutation.update.ReviewUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toEntity(ReviewUpdate update);

}
