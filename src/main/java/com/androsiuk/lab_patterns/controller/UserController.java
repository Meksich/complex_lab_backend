package com.androsiuk.lab_patterns.controller;

import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.entity.User;
import com.androsiuk.lab_patterns.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    // private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<User>> get(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id){
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id){
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}
