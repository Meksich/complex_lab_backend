package com.androsiuk.lab_patterns.controller;

import com.androsiuk.lab_patterns.DTO.BookDTO;
import com.androsiuk.lab_patterns.DTO.LoanDTO;
import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.mapper.LoanMapper;
import com.androsiuk.lab_patterns.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/loans")
@AllArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> get(){
        List<LoanDTO> dtoList = loanService.getAll().stream().map(loanMapper::map).toList();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LoanDTO> get(@PathVariable Integer id) {
        return new ResponseEntity<>(loanMapper.map(loanService.get(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoanDTO> create(@RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<>(loanMapper.map(loanService.issueBook(loanDTO)), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LoanDTO> update(@PathVariable Integer id, @RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<>(loanMapper.map(loanService.returnBook(id, loanDTO)), HttpStatus.OK);
    }
}
