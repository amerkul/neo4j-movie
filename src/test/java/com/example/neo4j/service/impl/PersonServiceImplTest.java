package com.example.neo4j.service.impl;

import com.example.neo4j.domain.Person;
import com.example.neo4j.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import reactor.test.StepVerifier;

@SpringBootTest
class PersonServiceImplTest extends Neo4jTestContainer {

    @Autowired
    PersonService personService;

    @DynamicPropertySource
    public static void registerNeo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", () -> neo4jContainer.getBoltUrl());
    }

    @BeforeEach
    private void createData() {
        try (Driver driver = GraphDatabase.driver(neo4jContainer.getBoltUrl());
             Session session = driver.session()) {
            session.run("""
                    CREATE (Keanu:Person {name:'Keanu Reeves', born:1964})
                    CREATE (Carrie:Person {name:'Carrie-Anne Moss', born:1967})
                    CREATE (Laurence:Person {name:'Laurence Fishburne', born:1961})
                    CREATE (Hugo:Person {name:'Hugo Weaving', born:1960})
                    CREATE (LillyW:Person {name:'Lilly Wachowski', born:1967})
                    CREATE (LanaW:Person {name:'Lana Wachowski', born:1965})
                    CREATE (JoelS:Person {name:'Joel Silver', born:1952})""");
        }
    }

    @AfterEach
    private void deleteData() {
        try (Driver driver = GraphDatabase.driver(neo4jContainer.getBoltUrl());
             Session session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n");
        }
    }

    @Test
    void update() {
        StepVerifier.create(personService.createOrUpdate(
                        new Person(1L,  1972, "Julia Roberts")))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void create() {
        StepVerifier.create(personService.createOrUpdate(
                        new Person(2002, "Anna Merkul")
                )).expectNextMatches(person -> person.getId() != null)
                .verifyComplete();
    }

    @Test
    void addFollowing() {
        StepVerifier.create(personService.addFollowing(
                        new Person(1L, 1964, "Keanu Reeves"), "Carrie-Anne Moss"
                )).expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void delete() {
        StepVerifier.create(personService.delete(6L))
                .verifyComplete();
    }

    @Test
    void retrieveAll() {
        StepVerifier.create(personService.retrieveAll())
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    void retrieveByName() {
        StepVerifier.create(personService.retrieveByName("Hugo Weaving"))
                .expectNextMatches(p -> "Hugo Weaving".equals(p.getName()))
                .verifyComplete();
    }

}
