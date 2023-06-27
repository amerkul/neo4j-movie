package com.example.neo4j.mutation.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonUpdate {

    private Long id;
    private Integer born;
    private String name;

}
