package com.androsiuk.lab_patterns.controller;

import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.entity.UserCatalog;
import com.androsiuk.lab_patterns.service.UserCatalogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user_catalogs")
@AllArgsConstructor
public class UserCatalogController {
    private final UserCatalogService userCatalogService;
    // private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<UserCatalog>> get(){
        return new ResponseEntity<>(userCatalogService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserCatalog> get(@PathVariable Integer id){
        return new ResponseEntity<>(userCatalogService.get(id), HttpStatus.OK);
    }
}