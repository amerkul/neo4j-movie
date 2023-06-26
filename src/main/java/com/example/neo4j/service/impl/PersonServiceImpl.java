package com.example.neo4j.service.impl;

import com.example.neo4j.domain.Person;
import com.example.neo4j.repository.PersonRepository;
import com.example.neo4j.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Override
    @Transactional
    public Mono<Person> create(Person person) {
        return repository.save(person);
    }

    @Override
    @Transactional
    public Mono<Person> update(Person person) {
        log.debug("Update person, Setting values: {}", person);
        return repository.updatePerson(person);
    }

    @Override
    @Transactional
    public Mono<Person> addFollowing(Person person, String followingName) {
        return repository.addFollowing(person.getName(), followingName);
    }

    @Override
    @Transactional
    public Mono<Void> delete(long id) {
        return this.repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Person> retrieveAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Person> retrieveByName(String name) {
        return this.repository.findByName(name);
    }

}
