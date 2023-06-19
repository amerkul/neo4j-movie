package com.example.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private String tagline;
    private String title;
    private Integer released;
    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private Set<Role> roles = new HashSet<>();
    @Relationship(type = "DIRECTED", direction = INCOMING)
    private Set<Person> directors = new HashSet<>();
    @Relationship(type = "WROTE", direction = INCOMING)
    private Set<Person> writers = new HashSet<>();
    @Relationship(type = "PRODUCED", direction = INCOMING)
    private Set<Person> producers = new HashSet<>();
    @Relationship(type = "REVIEWED", direction = INCOMING)
    private List<Person> reviewers;


}
