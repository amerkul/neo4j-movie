package com.example.neo4j.fetcher;

import com.example.neo4j.domain.Person;
import com.example.neo4j.service.PersonService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DgsComponent
@AllArgsConstructor
public class PersonFetcher {

    private final PersonService service;

    @DgsQuery
    public Flux<Person> people() {
        return this.service.retrieveAll();
    }

    @DgsQuery
    public Mono<Person> personByName(String name) {
        return this.service.retrieveByName(name);
    }

}
