package com.reactive.api.rest.controllers;

import com.reactive.api.rest.documents.Contacts;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    private Contacts savedContact;

    @Test
    @Order(0)
    public void saveContactTest(){
        Flux<Contacts> contactsFlux = webTestClient.post()
                .uri("/api/v1/contacts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Contacts("webtest", "w@gmail.com","888090909")))
                .exchange()
                .expectStatus().isAccepted()
                .returnResult(Contacts.class).getResponseBody()
                .log();

        contactsFlux.next().subscribe(contact -> {
            this.savedContact = contact;
        }
    );
    Assertions.assertNotNull(savedContact);
    }

    @Test
    @Order(1)
    public void testObtenerContactoPorEmail(){
        Flux<Contacts> contactoFlux = webTestClient.get()
                .uri("/api/v1/contacts/byEmail/{email}","w@gmail.com")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Contacts.class).getResponseBody()
                .log();

        StepVerifier.create(contactoFlux)
                .expectSubscription()
                .expectNextMatches(contact -> contact.getEmail().equals("w@gmail.com"))
                .verifyComplete();
    }

    @Test
    @Order(2)
    public void testListarContactos(){
        Flux<Contacts> contactosFlux = webTestClient.get()
                .uri("/api/v1/contacts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .returnResult(Contacts.class).getResponseBody()
                .log();

        StepVerifier.create(contactosFlux)
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @Order(3)
    public void testEliminarContacto(){
        Flux<Void> flux = webTestClient.delete()
                .uri("/api/v1/contacts/{id}",savedContact.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .returnResult(Void.class).getResponseBody();

        StepVerifier.create(flux)
                .expectSubscription()
                .verifyComplete();
    }
}
