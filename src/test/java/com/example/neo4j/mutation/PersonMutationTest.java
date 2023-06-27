package com.example.neo4j.mutation;

import com.example.neo4j.domain.Person;
import com.example.neo4j.mapper.PersonMapper;
import com.example.neo4j.mutation.input.PersonInput;
import com.example.neo4j.mutation.update.PersonUpdate;
import com.example.neo4j.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonMutationTest {

    @InjectMocks
    PersonMutation mutation;
    @Mock
    PersonService service;
    @Mock
    PersonMapper mapper;

    @Test
    void delete() {
        when(service.delete(anyLong())).thenReturn(Mono.empty());
        Mono<String> deletedPerson = mutation.delete(1L);
        StepVerifier.create(deletedPerson)
                .expectNextMatches("Person was deleted by id = 1"::equals)
                .verifyComplete();
    }

    @Test
    void newPerson() {
        PersonInput input = new PersonInput(1977, "Ilya Anderson");
        Person newPerson = new Person(3L, 1977, "Ilya Anderson");
        when(mapper.toEntity(any(PersonInput.class))).thenReturn(newPerson);
        when(service.createOrUpdate(any(Person.class))).thenReturn(Mono.just(newPerson));
        Mono<Person> person = mutation.newPerson(input);
        StepVerifier.create(person)
                .expectNextMatches(p -> "Ilya Anderson".equals(p.getName()))
                .verifyComplete();
    }

    @Test
    void updatePerson() {
        PersonUpdate update = new PersonUpdate(2L, 1977, "Ilya Anderson");
        Person updatePerson = new Person(2L, 1977, "Ilya Anderson");
        when(mapper.toEntity(any(PersonUpdate.class))).thenReturn(updatePerson);
        when(service.createOrUpdate(any(Person.class))).thenReturn(Mono.just(updatePerson));
        Mono<Person> person = mutation.updatePerson(update);
        StepVerifier.create(person)
                .expectNextMatches(p -> "Ilya Anderson".equals(p.getName()))
                .verifyComplete();
    }

    @Test
    void createFollowing() {
        PersonUpdate update = new PersonUpdate(1L, 2002, "Anna Merkul");
        Person updatePerson = new Person(1L, 2002, "Anna Merkul");
        when(mapper.toEntity(any(PersonUpdate.class))).thenReturn(updatePerson);
        when(service.addFollowing(any(Person.class), anyString())).thenReturn(Mono.just(updatePerson));
        Mono<Person> person = mutation.createFollowing(update, "Julia Merkul");
        StepVerifier.create(person)
                .expectNextMatches(p -> "Anna Merkul".equals(p.getName()))
                .verifyComplete();
    }

}
