package com.example.neo4j.mutation;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.mapper.MovieMapper;
import com.example.neo4j.mutation.input.MovieInput;
import com.example.neo4j.mutation.update.MovieUpdate;
import com.example.neo4j.service.MovieService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@DgsComponent
@AllArgsConstructor
public class MovieMutation {

    private final MovieService service;
    private final MovieMapper mapper;

    @DgsData(parentType = "Mutation", field = "deleteMovie")
    public Mono<String> deleteMovie(long id) {
        return this.service.delete(id).thenReturn("Movie was deleted by id = " + id);
    }

    @DgsData(parentType = "Mutation", field = "newMovie")
    public Mono<Movie> newMovie(MovieInput input) {
        Movie movie = mapper.toEntity(input);
        return this.service.createOrUpdate(movie);
    }

    @DgsData(parentType = "Mutation", field = "updateMovie")
    public Mono<Movie> updateMovie(MovieUpdate update) {
        Movie movie = mapper.toEntity(update);
        return this.service.createOrUpdate(movie);
    }

}
