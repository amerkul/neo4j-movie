package com.example.neo4j.mutation;

import com.example.neo4j.domain.Person;
import com.example.neo4j.mapper.PersonMapper;
import com.example.neo4j.mutation.input.PersonInput;
import com.example.neo4j.mutation.update.PersonUpdate;
import com.example.neo4j.service.PersonService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@DgsComponent
@AllArgsConstructor
public class PersonMutation {

    private final PersonService service;
    private final PersonMapper mapper;

    @DgsData(parentType = "Mutation", field = "deletePerson")
    public Mono<String> delete(long id) {
        return this.service.delete(id).thenReturn("Person was deleted by id = " + id);
    }

    @DgsData(parentType = "Mutation", field = "newPerson")
    public Mono<Person> newPerson(PersonInput input) {
        log.debug("PersonInput {} ", input);
        Person person = mapper.toEntity(input);
        return this.service.create(person);
    }

    @DgsData(parentType = "Mutation", field = "updatePerson")
    public Mono<Person> updatePerson(PersonUpdate update) {
        Person person = mapper.toEntity(update);
        return this.service.update(person);
    }

    @DgsData(parentType = "Mutation", field = "createFollowing")
    public Mono<Person> createFollowing(PersonUpdate update, String followingName) {
        Person person = mapper.toEntity(update);
        return this.service.addFollowing(person, followingName);
    }

}
