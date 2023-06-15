package com.example.neo4j.repository;

import com.example.neo4j.domain.Person;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface PersonRepository extends ReactiveNeo4jRepository<Person, Long> {

}
