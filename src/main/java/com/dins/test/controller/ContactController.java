package com.dins.test.controller;

import com.dins.test.api.request.ContactRequest;
import com.dins.test.api.request.EditContactRequest;
import com.dins.test.repository.UserRepository;
import com.dins.test.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @PostMapping
    public ResponseEntity<?> addContact(@Valid @RequestBody ContactRequest contactRequest){
        return contactService.addContact(contactRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContact(@PathVariable long id){
        return contactService.getContactById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable long id){
        return contactService.deleteContact(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editContact(@PathVariable long id, @RequestBody EditContactRequest editContactRequest){
        return contactService.editContact(id, editContactRequest);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getContactByNumber(@RequestParam String number){
        return contactService.getContactByNumber(number);
    }

}
