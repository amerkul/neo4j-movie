package com.example.neo4j.mapper;

import com.example.neo4j.domain.Person;
import com.example.neo4j.mutation.input.PersonInput;
import com.example.neo4j.mutation.update.PersonUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {PersonMapper.class})
public interface PersonMapper {

    Person toEntity(PersonInput input);
    Person toEntity(PersonUpdate update);

}
