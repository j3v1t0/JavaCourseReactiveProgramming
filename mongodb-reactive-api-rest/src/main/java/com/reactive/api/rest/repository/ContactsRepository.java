package com.reactive.api.rest.repository;

import com.reactive.api.rest.documents.Contacts;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ContactsRepository extends ReactiveMongoRepository<Contacts, String> {

    Mono<Contacts> findFirstByEmail(String email);
    Mono<Contacts> findAllByPhoneOrFullname(String phoneOrFullname);
}
