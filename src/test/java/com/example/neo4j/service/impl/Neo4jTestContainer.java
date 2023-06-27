package com.example.neo4j.service.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class Neo4jTestContainer {

    private static DockerImageName image = DockerImageName.parse("neo4j:5.8.0");
    @Container
    static Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>(image).withoutAuthentication();

    @BeforeAll
    static void setUp() {
        neo4jContainer.start();
    }

    @AfterAll
    static void destroy() {
        neo4jContainer.stop();
    }

}
