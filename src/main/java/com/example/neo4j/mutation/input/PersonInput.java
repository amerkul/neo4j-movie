package com.example.neo4j.mutation.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInput {

    private Integer born;
    private String name;

}
