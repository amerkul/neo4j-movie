package com.example.neo4j.mapper;

import com.example.neo4j.domain.Role;
import com.example.neo4j.mutation.update.RoleUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleUpdate update);

}
