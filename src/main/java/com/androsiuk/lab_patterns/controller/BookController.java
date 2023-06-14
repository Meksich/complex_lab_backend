package com.androsiuk.lab_patterns.controller;

import com.androsiuk.lab_patterns.DTO.BookDTO;
import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    // private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<Book>> get(){
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> get(@PathVariable Integer id){
        return new ResponseEntity<>(bookService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.create(book), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.update(id, book), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Book> delete(@PathVariable Integer id){
        return new ResponseEntity<>(bookService.delete(id), HttpStatus.OK);
    }
}
