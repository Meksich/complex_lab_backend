package com.androsiuk.lab_patterns.controller;

import com.androsiuk.lab_patterns.entity.UserCatalog;
import com.androsiuk.lab_patterns.entity.UserCategory;
import com.androsiuk.lab_patterns.service.UserCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user_categories")
@AllArgsConstructor
public class UserCategoryController {
    private final UserCategoryService userCategoryService;
    // private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<UserCategory>> get(){
        return new ResponseEntity<>(userCategoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserCategory> get(@PathVariable Integer id){
        return new ResponseEntity<>(userCategoryService.get(id), HttpStatus.OK);
    }

}
