package com.dins.test.service;

import com.dins.test.api.request.ContactRequest;
import com.dins.test.api.request.EditContactRequest;
import com.dins.test.api.response.ContactResponse;
import com.dins.test.api.response.ErrorResponse;
import com.dins.test.entity.Contact;
import com.dins.test.entity.User;
import com.dins.test.repository.ContactRepository;
import com.dins.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(UserRepository userRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    public ResponseEntity<?> addContact(ContactRequest contactRequest) {
        Optional<User> userOptional = userRepository.findById(Long.parseLong(contactRequest.getUserId()));
        if (userOptional.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("User with id: " + contactRequest.getUserId() + " not found!"), HttpStatus.NOT_FOUND);
        }
        Contact contact = new Contact();
        contact.setName(contactRequest.getContactName());
        contact.setNumber(contactRequest.getContactNumber());
        contact.setUser(userOptional.get());
        contactRepository.save(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> getContactById(long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("Contact with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
        Contact contact = contactOptional.get();
        ContactResponse contactResponse = new ContactResponse(contact.getId(), contact.getName(), contact.getNumber());
        return new ResponseEntity<>(contactResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteContact(long id){
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("Contact with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
        Contact contact = contactOptional.get();
        contactRepository.delete(contact);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> editContact(long id, EditContactRequest editContactRequest){
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("Contact with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
        Contact contact = contactOptional.get();
        if (editContactRequest.getContactName() != null){
            contact.setName(editContactRequest.getContactName());
        }
        if (editContactRequest.getContactNumber() != null){
            contact.setNumber(editContactRequest.getContactNumber());
        }

        contactRepository.save(contact);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getContactByNumber(String number) {
        Contact contact = contactRepository.findByNumber(number);
        if (contact == null){
            return new ResponseEntity<>(new ErrorResponse("Contact with number: " + number + " not found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ContactResponse(contact.getId(), contact.getName(), contact.getNumber()), HttpStatus.OK);
    }
}
