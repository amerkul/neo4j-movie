package com.example.neo4j.domain;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.Set;

@RelationshipProperties
public class Role {

    @RelationshipId
    private Long id;
    private Set<String> roles;
    @TargetNode
    private Movie movie;

}
