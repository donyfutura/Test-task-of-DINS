package com.dins.test.controller;

import com.dins.test.api.request.IdRequest;
import com.dins.test.api.request.UserEditRequest;
import com.dins.test.api.request.UserRequest;
import com.dins.test.entity.User;
import com.dins.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> allUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest){
        return userService.addUser(userRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        return userService.deleteUserById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable long id, @Valid @RequestBody UserEditRequest userEditRequest){
        return userService.editUserById(id, userEditRequest);
    }

    @GetMapping("/{id}/contacts")
    public ResponseEntity<?> getContacts(@PathVariable long id){
        return userService.getContactsById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getUserByName(@RequestParam String name){
        System.out.println("controller" + name);
        return userService.getUserByName(name);
    }



}
