package com.androsiuk.lab_patterns.service;

import com.androsiuk.lab_patterns.DTO.LoanDTO;
import com.androsiuk.lab_patterns.entity.Loan;
import com.androsiuk.lab_patterns.entity.MonthlyReport;
import com.androsiuk.lab_patterns.observer.*;
import com.androsiuk.lab_patterns.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class LoanService extends com.androsiuk.lab_patterns.service.Service<Loan> {
    private LoanRepository loanRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private MonthlyReportRepository monthlyReportRepository;
    private LibraryRepository libraryRepository;
    private UserCatalogRepository userCatalogRepository;
    private List<Observer> observerList;

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
        updateObserversList(loan);
        create(loan);
        notifyAllObservers(loan);
        return loan;
    }

    public Loan returnBook(Integer id, LoanDTO loanDTO) {
        Loan loan = loanRepository.getOne(id);
        loan.setReturnDate(new Date());
        loan.setUserBalanceDeduction(0.0);
        updateObserversList(loan);
        update(id, loan);
        notifyAllObservers(loan);
        return loan;
    }

    public void notifyAllObservers(Loan loan){
        for (Observer observer : observerList){
            observer.update(loan.getReturnDate(), loan.getBook().getPledgeCost(), loan.getUserBalanceDeduction());
        }
    }


    public void updateObserversList(Loan loan) {
        observerList.add(new BookObserver(bookRepository, loan.getBook()));
        List<MonthlyReport> monthlyReports = loan.getBook().getLibrary().getMonthlyReports().stream().toList();
        observerList.add(new ReportObserver(monthlyReportRepository, monthlyReports.get(monthlyReports.size()-1)));
        observerList.add(new UserObserver(userRepository, loan.getUser()));
        observerList.add(new LibraryObserver(libraryRepository, loan.getBook().getLibrary()));
        observerList.add(new UserCatalogObserver(userCatalogRepository, loan.getUser().getUserCatalog()));
    }
}
