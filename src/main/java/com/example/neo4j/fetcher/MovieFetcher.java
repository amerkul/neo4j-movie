package com.example.neo4j.fetcher;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.service.MovieService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@DgsComponent
@AllArgsConstructor
public class MovieFetcher {

    private final MovieService service;

    @DgsQuery
    public Flux<Movie> movies() {
        return this.service.retrieveAll();
    }

}
