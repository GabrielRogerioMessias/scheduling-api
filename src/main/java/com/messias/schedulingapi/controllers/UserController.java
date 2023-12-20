package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.domain.User;
import com.messias.schedulingapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> userList = userService.findAll();
        return ResponseEntity.ok().body(userList);
    }

    @DeleteMapping(value = "{idUser}")
    public ResponseEntity<Void> delete(@PathVariable Integer idUser) {
        userService.delete(idUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{idUser}")
    public ResponseEntity<User> findById(@PathVariable Integer idUser) {
        User userResult = userService.findById(idUser);
        return ResponseEntity.ok().body(userResult);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User newUser) {
        User user = userService.insert(newUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("{idUser}")
    public ResponseEntity<User> update(@PathVariable Integer idUser, @RequestBody User updateUser) {
        userService.update(idUser, updateUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
