package com.example.neo4j.mutation;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.mapper.MovieMapper;
import com.example.neo4j.mutation.input.MovieInput;
import com.example.neo4j.mutation.update.MovieUpdate;
import com.example.neo4j.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieMutationTest {

    @InjectMocks
    MovieMutation mutation;
    @Mock
    MovieService service;
    @Mock
    MovieMapper mapper;

    @Test
    void deleteMovie() {
        when(service.delete(anyLong())).thenReturn(Mono.empty());
        Mono<String> deletedPerson = mutation.deleteMovie(1L);
        StepVerifier.create(deletedPerson)
                .expectNextMatches("Movie was deleted by id = 1"::equals)
                .verifyComplete();
    }

    @Test
    void newMovie() {
        MovieInput input = new MovieInput("Tagline_1", "Title_1", 2000);
        Movie newMovie = new Movie(3L, "Tagline_1", "Title_1", 2000);
        when(mapper.toEntity(any(MovieInput.class))).thenReturn(newMovie);
        when(service.createOrUpdate(any(Movie.class))).thenReturn(Mono.just(newMovie));
        Mono<Movie> movie = mutation.newMovie(input);
        StepVerifier.create(movie)
                .expectNextMatches(m -> "Title_1".equals(m.getTitle()))
                .verifyComplete();
    }

    @Test
    void updateMovie() {
        MovieUpdate update = new MovieUpdate(3L, "Tagline_3", "Title_3", 2000);
        Movie updateMovie = new Movie(3L, "Tagline_3", "Title_3", 2000);
        when(mapper.toEntity(any(MovieUpdate.class))).thenReturn(updateMovie);
        when(service.createOrUpdate(any(Movie.class))).thenReturn(Mono.just(updateMovie));
        Mono<Movie> movie = mutation.updateMovie(update);
        StepVerifier.create(movie)
                .expectNextMatches(m -> "Title_3".equals(m.getTitle()))
                .verifyComplete();
    }

}
