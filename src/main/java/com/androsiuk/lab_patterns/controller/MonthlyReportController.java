package com.androsiuk.lab_patterns.controller;

import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.entity.MonthlyReport;
import com.androsiuk.lab_patterns.service.MonthlyReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/monthly_reports")
@AllArgsConstructor
public class MonthlyReportController {
    private final MonthlyReportService monthlyReportService;
    // private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<MonthlyReport>> get(){
        return new ResponseEntity<>(monthlyReportService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MonthlyReport> get(@PathVariable Integer id){
        return new ResponseEntity<>(monthlyReportService.get(id), HttpStatus.OK);
    }
}
