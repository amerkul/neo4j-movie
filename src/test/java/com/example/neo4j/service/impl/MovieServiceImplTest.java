package com.example.neo4j.service.impl;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.service.MovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import reactor.test.StepVerifier;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

@SpringBootTest
class MovieServiceImplTest extends Neo4jTestContainer {

    @Autowired
    MovieService movieService;

    @DynamicPropertySource
    public static void registerNeo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", () -> neo4jContainer.getBoltUrl());
    }

    @BeforeEach
    private void createData() {
        try (Driver driver = GraphDatabase.driver(neo4jContainer.getBoltUrl());
             Session session = driver.session()) {
            session.run("""
                    CREATE (TheMatrixReloaded:Movie {title:'The Matrix Reloaded', released:2003, tagline:'Free your mind'})
                    CREATE (TheMatrixRevolutions:Movie {title:'The Matrix Revolutions', released:2003, tagline:'Everything that has a beginning has an end'})
                    CREATE (TheDevilsAdvocate:Movie {title:"The Devil's Advocate", released:1997, tagline:'Evil has its winning ways'})""");
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
    void create() {
        StepVerifier.create(movieService.createOrUpdate(
                        new Movie("Tagline_1", "Title_1", 2000)
                )).expectNextMatches(movie -> movie.getId() != null)
                .verifyComplete();
    }

    @Test
    void update() {
        StepVerifier.create(movieService.createOrUpdate(
                        new Movie(1L, "Tagline_1", "The Matrix Reloaded", 2003)
                )).expectNextMatches(movie -> "Tagline_1".equals(movie.getTagline()))
                .verifyComplete();
    }

    @Test
    void delete() {
        StepVerifier.create(movieService.delete(1L))
                .verifyComplete();
    }

    @Test
    void retrieveAll() {
        StepVerifier.create(movieService.retrieveAll())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void retrieveByTitle() {
        StepVerifier.create(movieService.retrieveByTitle("The Matrix Reloaded"))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void retrieveByReleasedBetween() {
        StepVerifier.create(movieService.retrieveByReleasedBetween(1900, 2000))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void countMovies() {
        StepVerifier.create(movieService.countMovies())
                .expectNext(3L)
                .verifyComplete();
    }

    @Test
    void retrieveByActedIn() {
        StepVerifier.create(movieService.retrieveByActedIn("Adam Ryler"))
                .expectNextCount(0)
                .verifyComplete();
    }

}
