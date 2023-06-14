package com.androsiuk.lab_patterns.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCategoryDTO {
    private Integer id;
    private String name;
    private Float discount;
}
