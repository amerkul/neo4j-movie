package com.example.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class Review {

    @RelationshipId
    @GeneratedValue
    private Long id;
    private Integer rating;
    private String summary;
    @TargetNode
    private Person person;

}
