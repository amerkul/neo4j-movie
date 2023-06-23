package com.example.neo4j.data;

import com.example.neo4j.domain.Person;
import reactor.core.publisher.Flux;

public class PersonData {

    public static Flux<Person> getPeople() {
        return Flux.just(
                new Person(1L, 2002, "Anna Merkul"),
                new Person(2L, 1993, "Julia Merkul")
        );
    }

}
