package com.reactive.api.rest.repository;

import com.reactive.api.rest.documents.Contacts;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactRepositoryTest {
    @Autowired
    private ContactsRepository contactsRepository;
    @Autowired
    private ReactiveMongoOperations mongoOperations;
    @BeforeAll
    public void insertData(){
        Contacts contact1 = new Contacts();
        contact1.setFullname("Test1");
        contact1.setEmail("c1@gmail.com");
        contact1.setPhone("133222");

        Contacts contact2 = new Contacts();
        contact2.setFullname("Test2");
        contact2.setEmail("c2@gmail.com");
        contact2.setPhone("233222");

        Contacts contact3 = new Contacts();
        contact3.setFullname("Test3");
        contact3.setEmail("c3@gmail.com");
        contact3.setPhone("333222");

        //Saving contacts
        StepVerifier.create(contactsRepository.insert(contact1).log())
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();

        StepVerifier.create(contactsRepository.save(contact2).log())
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();

        StepVerifier.create(contactsRepository.save(contact3).log())
                .expectSubscription()
                .expectNextMatches(contact -> (contact.getId() != null))
                .verifyComplete();
    }
    @Test
    @Order(1)
    public void listContactTest(){
        StepVerifier.create(contactsRepository.findAll().log())
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }
    @Test
    @Order(2)
    public void findByEmailContactTest(){
        StepVerifier.create(contactsRepository.findFirstByEmail("c1@gmail.com").log())
                .expectSubscription()
                .expectNextMatches(contact -> contact.getEmail().equals("c1@gmail.com"))
                .verifyComplete();
    }
    @Test
    @Order(3)
    public void updateContactTest(){
        Mono<Contacts> updatedContact = contactsRepository.findFirstByEmail("c1@gmail.com")
                .map(contact -> {
                    contact.setPhone("1111111");
                    return contact;
                })
                .flatMap(contact -> {
                    return contactsRepository.save(contact);
                });

        StepVerifier.create(updatedContact.log())
                .expectSubscription()
                .expectNextMatches(contact -> (contact.getPhone().equals("1111111")))
                .verifyComplete();
    }
    @Test
    @Order(4)
    public void deleteContactByIdTest(){
        Mono<Void> deletedContact = contactsRepository.findFirstByEmail("c2@gmail.com")
                .flatMap(contact -> {
                    return contactsRepository.deleteById(contact.getId());
                }).log();

        StepVerifier.create(deletedContact)
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @Order(5)
    public void deleteContact(){
        Mono<Void> deletedContact = contactsRepository.findFirstByEmail("c3@gmail.com")
                .flatMap(contact -> contactsRepository.delete(contact));

        StepVerifier.create(deletedContact)
                .expectSubscription()
                .verifyComplete();
    }
    @AfterAll
    public void cleanData(){
        Mono<Void> deletedElements = contactsRepository.deleteAll();
        StepVerifier.create(deletedElements.log())
                .expectSubscription()
                .verifyComplete();
    }
}
