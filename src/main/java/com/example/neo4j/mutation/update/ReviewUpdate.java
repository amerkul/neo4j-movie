package com.example.neo4j.mutation.update;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdate {

    private Long id;
    private Integer rating;
    private String summary;
    private Person person;

}
