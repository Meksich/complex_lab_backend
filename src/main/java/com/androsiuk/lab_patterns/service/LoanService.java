package com.androsiuk.lab_patterns.service;

import com.androsiuk.lab_patterns.DTO.LoanDTO;
import com.androsiuk.lab_patterns.entity.Loan;
import com.androsiuk.lab_patterns.repository.BookRepository;
import com.androsiuk.lab_patterns.repository.LoanRepository;
import com.androsiuk.lab_patterns.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class LoanService extends com.androsiuk.lab_patterns.service.Service<Loan> {
    private LoanRepository loanRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Override
    public JpaRepository<Loan, Integer> getRepository() {
        return loanRepository;
    }

    public Loan issueBook(LoanDTO loanDTO) {
        Loan loan = new Loan();
        loan.setBook(bookRepository.getOne(loanDTO.getBookId()));
        loan.setUser(userRepository.getOne(loanDTO.getUserId()));
        loan.setIssueDate(new Date());
        loan.setReturnDate(loanDTO.getReturnDate());
        create(loan);
        return loan;
    }

    public Loan returnBook(Integer id, LoanDTO loanDTO) {
        Loan loan = loanRepository.getOne(id);
        loan.setReturnDate(new Date());
        update(id, loan);
        return loan;
    }
}
