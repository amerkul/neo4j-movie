package com.example.neo4j.mutation.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieInput {

    private String tagline;
    private String title;
    private Integer released;

}
