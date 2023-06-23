package com.example.neo4j.mutation.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdate {

    private Long id;
    private List<String> roles;
    private PersonUpdate person;

}
