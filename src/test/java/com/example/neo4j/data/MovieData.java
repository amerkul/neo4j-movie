package com.example.neo4j.data;

import com.example.neo4j.domain.Movie;
import reactor.core.publisher.Flux;

public class MovieData {

    public static Flux<Movie> getMovies() {
        return Flux.just(
                new Movie(1L, "Tagline_1", "Title_1", 2000),
                new Movie(2L, "Tagline_2", "Title_2", 2001)
        );
    }

}
