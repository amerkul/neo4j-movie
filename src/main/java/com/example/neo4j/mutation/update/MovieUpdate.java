package com.example.neo4j.mutation.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdate {

    private Long id;
    private String tagline;
    private String title;
    private Integer released;

}
