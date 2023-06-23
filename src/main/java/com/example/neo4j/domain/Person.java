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

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private Integer born;
    private String name;
    @Relationship(type = "ACTED_IN", direction = OUTGOING)
    private List<Role> actedInMovies = new ArrayList<>();
    @Relationship(type = "REVIEWED", direction = OUTGOING)
    private List<Review> reviewedMovies = new ArrayList<>();
    @Relationship(type = "DIRECTED", direction = OUTGOING)
    private List<Movie> directed = new ArrayList<>();
    @Relationship(type = "WROTE", direction = OUTGOING)
    private List<Movie> wrote = new ArrayList<>();
    @Relationship(type = "PRODUCED", direction = OUTGOING)
    private List<Movie> produced = new ArrayList<>();
    @Relationship(type = "FOLLOWS")
    private List<Person> followings = new ArrayList<>();

}
