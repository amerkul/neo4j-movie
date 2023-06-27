package com.example.neo4j.fetcher;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.service.MovieService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DgsComponent
@AllArgsConstructor
public class MovieFetcher {

    private final MovieService service;

    @DgsQuery
    public Flux<Movie> movies() {
        return this.service.retrieveAll();
    }

    @DgsQuery
    public Mono<Movie> movieByTitle(String title) {
        return this.service.retrieveByTitle(title);
    }

    @DgsQuery
    public Flux<Movie> movieByReleasedBetween(int from, int to) {
        return this.service.retrieveByReleasedBetween(from, to);
    }

    @DgsQuery
    public Mono<Long> countMovies() {
        return this.service.countMovies();
    }

    @DgsQuery
    public Flux<Movie> moviesByPersonActedIn(String name) {
        return this.service.retrieveByActedIn(name);
    }

}
