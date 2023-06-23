package com.example.neo4j.service.impl;

import com.example.neo4j.domain.Movie;
import com.example.neo4j.repository.MovieRepository;
import com.example.neo4j.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;

    @Override
    @Transactional
    public Mono<Movie> createOrUpdate(Movie movie) {
        return this.repository.save(movie);
    }

    @Override
    @Transactional
    public Mono<Void> delete(long id) {
        return this.repository.deleteById(id);
    }

    @Override
    @Transactional
    public Flux<Movie> retrieveAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public Mono<Movie> retrieveByTitle(String title) {
        return this.repository.findByTitle(title);
    }

    @Override
    @Transactional
    public Flux<Movie> retrieveByReleasedBetween(int from, int to) {
        return this.repository.findByReleasedBetween(from, to);
    }

    @Override
    @Transactional
    public Mono<Long> countMovies() {
        return this.repository.count();
    }

    @Override
    @Transactional
    public Flux<Movie> retrieveByActedIn(String name) {
        return this.repository.findByPersonActedIn(name);
    }

}
