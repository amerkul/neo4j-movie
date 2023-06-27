package com.example.neo4j.fetcher;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieFetcherTest {

    @InjectMocks
    MovieFetcher fetcher;
    @Mock
    MovieService service;

    @Test
    void movies() {
        when(service.retrieveAll()).thenReturn(getMovies());
        Flux<Movie> movies = fetcher.movies();
        StepVerifier.create(movies)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void movieByTitle() {
        when(service.retrieveByTitle("Title_1"))
                .thenReturn(getMovies().filter(m -> "Title_1".equals(m.getTitle())).next());
        Mono<Movie> movie = fetcher.movieByTitle("Title_1");
        StepVerifier.create(movie)
                .expectNextMatches(m -> "Title_1".equals(m.getTitle()))
                .verifyComplete();
    }

    @Test
    void movieByReleasedBetween() {
        when(service.retrieveByReleasedBetween(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(getMovies());
        Flux<Movie> movies = fetcher.movieByReleasedBetween(1999, 2004);
        StepVerifier.create(movies)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void countMovies() {
        when(service.countMovies())
                .thenReturn(getMovies().count());
        Mono<Long> count = fetcher.countMovies();
        StepVerifier.create(count)
                .expectNextMatches(c -> c == 2)
                .verifyComplete();
    }

    @Test
    void moviesByPersonActedIn() {
        when(service.retrieveByActedIn(Mockito.anyString()))
                .thenReturn(getMovies());
        Flux<Movie> movies = fetcher.moviesByPersonActedIn("Anna");
        StepVerifier.create(movies)
                .expectNextCount(2)
                .verifyComplete();
    }

    private Flux<Movie> getMovies() {
        return Flux.just(
                new Movie(1L, "Tagline_1", "Title_1", 2000),
                new Movie(2L, "Tagline_2", "Title_2", 2001)
        );
    }

}
