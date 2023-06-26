package com.example.neo4j.fetcher;

import com.example.neo4j.domain.Person;
import com.example.neo4j.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonFetcherTest {

    @InjectMocks
    PersonFetcher fetcher;
    @Mock
    PersonService service;

    @Test
    void people() {
        when(service.retrieveAll()).thenReturn(getPeople());
        Flux<Person> people = fetcher.people();
        StepVerifier.create(people)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void personByName() {
        when(service.retrieveByName("Anna Merkul"))
                .thenReturn(getPeople().filter(p -> "Anna Merkul".equals(p.getName())).next());
        Mono<Person> person = fetcher.personByName("Anna Merkul");
        StepVerifier.create(person)
                .expectNextMatches(p -> "Anna Merkul".equals(p.getName()))
                .verifyComplete();
    }

    private Flux<Person> getPeople() {
        return Flux.just(
                new Person(1L, 2002, "Anna Merkul"),
                new Person(2L, 1993, "Julia Merkul")
        );
    }

}
