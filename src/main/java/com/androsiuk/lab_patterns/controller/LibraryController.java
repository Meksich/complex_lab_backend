package com.androsiuk.lab_patterns.controller;

import com.androsiuk.lab_patterns.entity.Library;
import com.androsiuk.lab_patterns.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/library")
@AllArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;
    // private final BookMapper bookMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Library> get(@PathVariable Integer id){
        return new ResponseEntity<>(libraryService.get(id), HttpStatus.OK);
    }
}
