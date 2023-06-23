package com.example.neo4j.mutation.update;

import com.example.neo4j.domain.Person;
import com.example.neo4j.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdate {

    private Long id;
    private String tagline;
    private String title;
    private Integer released;
    private List<RoleUpdate> roles;
    private List<PersonUpdate> directors;
    private List<PersonUpdate> writers;
    private List<PersonUpdate> producers;
    private List<ReviewUpdate> reviews;

    public MovieUpdate(Long id, String tagline, String title, Integer released) {
        this.id = id;
        this.tagline = tagline;
        this.title = title;
        this.released = released;
    }

}
