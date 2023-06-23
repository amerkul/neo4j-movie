package com.example.neo4j.repository;

import com.example.neo4j.domain.Movie;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieRepository extends ReactiveNeo4jRepository<Movie, Long> {

    Mono<Movie> findByTitle(String title);
    Flux<Movie> findByReleasedBetween(int from, int to);
    @Query(value = """
            MATCH (p:Person {name: $name})-[:ACTED_IN]->(movies) RETURN movies""")
    Flux<Movie> findByPersonActedIn(@Param("name") String name);
    @Query(value = """
            MATCH (m:Movie) WHERE id(m) = :#{#movie.id}
            SET m.title = :#{#movie.title},
            m.released = :#{#movie.released},
            m.tagline = :#{#movie.tagline}
            RETURN m""")
    Mono<Movie> updateMovie(Movie movie);

}
