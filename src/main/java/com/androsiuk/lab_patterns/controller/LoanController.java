package com.androsiuk.lab_patterns.controller;

import com.androsiuk.lab_patterns.entity.Loan;
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
    // private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<Loan>> get(){
        return new ResponseEntity<>(loanService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Loan> get(@PathVariable Integer id) {
        return new ResponseEntity<>(loanService.get(id), HttpStatus.OK);
    }
}
