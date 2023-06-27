package com.example.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class Role {

    @RelationshipId
    @GeneratedValue
    private Long id;
    private List<String> roles;
    @TargetNode
    private Person person;

}
