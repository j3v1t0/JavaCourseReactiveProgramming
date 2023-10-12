package com.reactive.api.rest.controllers;

import com.reactive.api.rest.documents.Contacts;
import com.reactive.api.rest.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class ContactsController {

    @Autowired
    private ContactsRepository contactsRepository;
    @GetMapping("/contacts")
    public Flux<Contacts> contactsList(){
        return contactsRepository.findAll();
    }
    @GetMapping("/contacts/{id}")
    public Mono<ResponseEntity<Contacts>> getContacts(@PathVariable String id){
        return contactsRepository.findById(id)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @GetMapping("/contacts/byEmail/{email}")
    public Mono<ResponseEntity<Contacts>> getContactsByEmail(@PathVariable String email){
        return contactsRepository.findFirstByEmail(email)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/contacts")
    public Mono<ResponseEntity<Contacts>> saveContact(@RequestBody Contacts contact){
        return contactsRepository.insert(contact)
                .map(saveContact -> new ResponseEntity<>(saveContact, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(contact, HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping("/contacts/{id}")
    public Mono<ResponseEntity<Contacts>> updateContact(@RequestBody Contacts contact, @PathVariable String id){
        return contactsRepository.findById(id)
                .flatMap(updatedContact -> {
                    contact.setId(id);
                    return contactsRepository.save(contact)
                            .map(contact1 -> new ResponseEntity<>(contact1, HttpStatus.ACCEPTED));
                })
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/contacts/{id}")
    public Mono<Void> deleteContact(@PathVariable String id){
        return contactsRepository.deleteById(id);
    }
}
