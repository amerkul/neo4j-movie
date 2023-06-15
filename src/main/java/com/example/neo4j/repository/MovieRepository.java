package com.example.neo4j.repository;

import com.example.neo4j.domain.Movie;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface MovieRepository extends ReactiveNeo4jRepository<Movie, Long> {

}
