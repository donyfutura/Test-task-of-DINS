package com.dins.test.service;

import com.dins.test.api.request.IdRequest;
import com.dins.test.api.request.UserEditRequest;
import com.dins.test.api.request.UserRequest;
import com.dins.test.api.response.ContactResponse;
import com.dins.test.api.response.ErrorResponse;
import com.dins.test.api.response.IdResponse;
import com.dins.test.api.response.UserResponse;
import com.dins.test.entity.Contact;
import com.dins.test.entity.User;
import com.dins.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getAllUsers(){
        List<UserResponse> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(new UserResponse(user.getId(), user.getName()));
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<?> getUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("User with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        UserResponse userResponse = new UserResponse(user.getId(), user.getName());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> addUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        userRepository.save(user);
        return new ResponseEntity<>(new IdResponse(user.getId()), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteUserById(long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("User with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> editUserById(long id, UserEditRequest userEditRequest) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("User with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
        User editedUser = user.get();
        editedUser.setName(userEditRequest.getName());
        userRepository.save(editedUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getContactsById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("User with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        List<ContactResponse> contacts = new ArrayList<>();
        for (Contact contact: user.getContacts()){
            contacts.add(new ContactResponse(contact.getId(), contact.getName(), contact.getNumber()));
        }
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    public ResponseEntity<?> getUserByName(String name) {
        List<User> userList = userRepository.findByPartOfName(name);
        System.out.println(userList);
        if (userList.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("User with name: " + name + " not found!"), HttpStatus.NOT_FOUND);
        }
        List<UserResponse> users = new ArrayList<>();
        for (User user: userList){
            users.add(new UserResponse(user.getId(), user.getName()));
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
