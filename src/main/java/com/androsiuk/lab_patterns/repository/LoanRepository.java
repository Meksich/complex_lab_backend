package com.androsiuk.lab_patterns.repository;

import com.androsiuk.lab_patterns.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  LoanRepository extends JpaRepository<Loan, Integer> {
}
