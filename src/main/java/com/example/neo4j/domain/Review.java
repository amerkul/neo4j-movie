package com.example.neo4j.domain;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Review {

    @RelationshipId
    private Long id;
    private Integer rating;
    private String summary;
    @TargetNode
    private Movie movie;

}
