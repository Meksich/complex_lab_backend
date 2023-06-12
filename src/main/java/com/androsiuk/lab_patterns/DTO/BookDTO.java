package com.androsiuk.lab_patterns.DTO;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    private String name;
    private String author;
    private Integer pages;
    private String genre;
    private Double pledgeCost;
    private Double rentalDefaultCost;
    private Double rentalExtraDayPrice;
    private Date inServiceSince;
    private Boolean isRented;
    private Date returnDate;
    private Integer library_id;
    private Set<Integer> loan_ids;
}
