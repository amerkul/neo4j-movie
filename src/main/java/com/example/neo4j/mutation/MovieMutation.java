package com.example.neo4j.mutation;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.mutation.input.MovieInput;
import com.example.neo4j.mutation.update.MovieUpdate;
import com.example.neo4j.service.MovieService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;

@DgsComponent
@AllArgsConstructor
public class MovieMutation {

    private final MovieService service;
    private final ModelMapper mapper;

    @DgsMutation
    public Mono<String> deleteMovie(@InputArgument long id) {
        return this.service.delete(id).thenReturn("Movie was deleted by id = " + id);
    }

    @DgsMutation
    public Mono<Movie> newMovie(@InputArgument MovieInput input) {
        Movie movie = mapper.map(input, Movie.class);
        return this.service.createOrUpdate(movie);
    }

    @DgsMutation
    public Mono<Movie> updateMovie(@InputArgument MovieUpdate update) {
        Movie movie = mapper.map(update, Movie.class);
        return this.service.createOrUpdate(movie);
    }

}
