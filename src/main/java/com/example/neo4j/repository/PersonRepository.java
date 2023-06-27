package com.example.neo4j.repository;

import com.example.neo4j.domain.Person;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveNeo4jRepository<Person, Long> {

    Mono<Person> findByName(String name);
    @Query(value = """
            MATCH (p:Person)
            WHERE id(p) = :#{#person.id}
            SET p.born = :#{#person.born},
            p.name = :#{#person.name}
            RETURN p""")
    Mono<Person> updatePerson(Person person);
    @Query(value = """
            MATCH (p:Person {name: $personName}), (f:Person {name: $followingName})
            MERGE (p)-[:FOLLOWS]->(f)
            RETURN p""")
    Mono<Person> addFollowing(@Param("personName") String personName,
                              @Param("followingName") String followingName);

}
