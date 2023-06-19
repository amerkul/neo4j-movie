package com.example.neo4j.mutation;

import com.example.neo4j.domain.Person;
import com.example.neo4j.mutation.input.PersonInput;
import com.example.neo4j.mutation.update.PersonUpdate;
import com.example.neo4j.service.PersonService;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PersonMutation {

    private final PersonService service;
    private final ModelMapper mapper;

    @DgsData(parentType = "Mutation", field = "deletePerson")
    public Mono<String> delete(long id) {
        return this.service.delete(id).thenReturn("Person was deleted by id = " + id);
    }

    @DgsMutation
    public Mono<Person> newPerson(@InputArgument PersonInput input) {
        Person person = mapper.map(input, Person.class);
        return this.service.createOrUpdate(person);
    }

    @DgsMutation
    public Mono<Person> updatePerson(@InputArgument PersonUpdate update) {
        Person person = mapper.map(update, Person.class);
        return this.service.createOrUpdate(person);
    }

}
