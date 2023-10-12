package com.reactive.api.rest.functional;

import com.reactive.api.rest.documents.Contacts;
import com.reactive.api.rest.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import static org.springframework.web.reactive.function.BodyInserters.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ContactHandler {
    @Autowired
    private ContactsRepository contactsRepository;

    private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
    private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

    //Contact list
    public Mono<ServerResponse> contactsList(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(contactsRepository.findAll(), Contacts.class);
    }

    //Get Contact by ID
    public Mono<ServerResponse> getContactById(ServerRequest request){
        String id = request.pathVariable("id");
        return contactsRepository.findById(id)
                .flatMap(contact -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact)))
                .switchIfEmpty(response404);
    }
    //Get Contact by Email
    public Mono<ServerResponse> getContactByEmail(ServerRequest request){
        String email = request.pathVariable("email");
        return contactsRepository.findFirstByEmail(email)
                .flatMap(contact -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact)))
                .switchIfEmpty(response404);
    }

    //Insert a contact
    public Mono<ServerResponse> insertContact(ServerRequest request){
        Mono<Contacts> saveContact = request.bodyToMono(Contacts.class);

        return saveContact
                .flatMap(contact -> contactsRepository.save(contact)
                        .flatMap(savedContact -> ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(savedContact))))
                .switchIfEmpty(response406);
    }

    //Update contact
    public Mono<ServerResponse> updateContact(ServerRequest request){
        Mono<Contacts> updateContact = request.bodyToMono(Contacts.class);

        String id = request.pathVariable("id");

        Mono<Contacts> updatedContact =updateContact
                .flatMap(contact -> contactsRepository.findById(id)
                        .flatMap(oldContact -> {
                            oldContact.setPhone(contact.getPhone());
                            oldContact.setFullname(contact.getFullname());
                            oldContact.setEmail(contact.getEmail());

                            return contactsRepository.save(oldContact);
                        }));
        return updatedContact
                .flatMap(contact -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact)))
                .switchIfEmpty(response404);
    }

    //Delete contact
    public Mono<ServerResponse> deleteContact(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Void> deletedContact = contactsRepository.deleteById(id);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deletedContact,Void.class);
    }
}
