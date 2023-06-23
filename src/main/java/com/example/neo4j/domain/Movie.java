package com.example.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

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
    private List<Role> roles = new ArrayList<>();
    @Relationship(type = "DIRECTED", direction = INCOMING)
    private List<Person> directors = new ArrayList<>();
    @Relationship(type = "WROTE", direction = INCOMING)
    private List<Person> writers = new ArrayList<>();
    @Relationship(type = "PRODUCED", direction = INCOMING)
    private List<Person> producers = new ArrayList<>();
    @Relationship(type = "REVIEWED", direction = INCOMING)
    private List<Review> reviews = new ArrayList<>();

}
