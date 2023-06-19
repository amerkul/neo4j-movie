package com.example.neo4j.service.impl;

import com.example.neo4j.domain.Person;
import com.example.neo4j.repository.PersonRepository;
import com.example.neo4j.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Override
    @Transactional
    public Mono<Person> createOrUpdate(Person person) {
        return repository.save(person);
    }

    @Override
    @Transactional
    public Mono<Void> delete(long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public Flux<Person> retrieveAll() {
        return repository.findAll();
    }

}
