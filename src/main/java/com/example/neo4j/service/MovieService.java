package com.example.neo4j.service;

import com.example.neo4j.domain.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Mono<Movie> createOrUpdate(Movie movie);
    Mono<Void> delete(long id);
    Flux<Movie> retrieveAll();

}
