package com.example.neo4j.service.impl;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.repository.MovieRepository;
import com.example.neo4j.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;

    @Override
    public Mono<Movie> createOrUpdate(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public Mono<Void> delete(long id) {
        return repository.deleteById(id);
    }

    @Override
    public Flux<Movie> retrieveAll() {
        return repository.findAll();
    }

}
