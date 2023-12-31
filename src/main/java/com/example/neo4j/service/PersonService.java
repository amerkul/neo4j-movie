package com.example.neo4j.service;

import com.example.neo4j.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<Person> createOrUpdate(Person person);
    Mono<Person> addFollowing(Person person, String followingName);
    Mono<Void> delete(long id);
    Flux<Person> retrieveAll();
    Mono<Person> retrieveByName(String name);

}
