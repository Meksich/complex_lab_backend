package com.androsiuk.lab_patterns.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCatalogDTO {
    private Integer id;
    private Integer usersWithDueRent;
    private Integer usersWithOverdueRent;
}
