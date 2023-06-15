package com.androsiuk.lab_patterns.service;

import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.entity.Loan;
import com.androsiuk.lab_patterns.entity.MonthlyReport;
import com.androsiuk.lab_patterns.repository.BookRepository;
import com.androsiuk.lab_patterns.repository.LibraryRepository;
import com.androsiuk.lab_patterns.repository.LoanRepository;
import com.androsiuk.lab_patterns.repository.MonthlyReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MonthlyReportService extends com.androsiuk.lab_patterns.service.Service<MonthlyReport> {
    private MonthlyReportRepository monthlyReportRepository;
    private LoanRepository loanRepository;
    private BookRepository bookRepository;
    private LibraryRepository libraryRepository;

    @Override
    public JpaRepository<MonthlyReport, Integer> getRepository() {
        return monthlyReportRepository;
    }

    @Override
    public List<MonthlyReport> getAll(){
        List<MonthlyReport> monthlyReports = getRepository().findAll();
        Date currDate = new Date();
        currDate.setMonth(currDate.getMonth()-1);
        if(currDate.getMonth() != Calendar.FEBRUARY) {
            currDate.setDate(30);
        } else {
            currDate.setDate(28);
        }

        if(monthlyReports.get(monthlyReports.size()-1).getMonth().before(currDate)){
            MonthlyReport newReport = createReport(currDate);
            monthlyReports.add(newReport);
            monthlyReportRepository.save(newReport);
        }
        return monthlyReports;
    }

    private MonthlyReport createReport(Date currDate){
        Date prevDate = new Date(currDate.getTime());
        prevDate.setMonth(prevDate.getMonth()-1);

        MonthlyReport newReport = new MonthlyReport();
        newReport.setMonth(currDate);

        List<Loan> loans = loanRepository.findAll();
        Double income = 0.0;
        int issued = 0;
        for(Loan loan : loans){
            if(loan.getReturnDate().before(currDate) && loan.getReturnDate().after(prevDate)){
                income += loan.getUserBalanceDeduction();
                issued += 1;
            }
        }
        newReport.setLibraryIncome(income);
        newReport.setBooksIssued(issued);
        if (issued!=0) {
            newReport.setAverageLoanIncome(income / issued);
        } else {
            newReport.setAverageLoanIncome(income);
        }
        newReport.setAverageUserIncome(0.0);

        List<Book> books = bookRepository.findAll();
        int newBooks = 0;
        for (Book book : books){
            if(book.getInServiceSince().before(currDate) && book.getInServiceSince().after(prevDate)){
                newBooks += 1;
            }
        }
        newReport.setNewBooks(newBooks);
        newReport.setBooksWithdrawn(0);

        newReport.setLibrary(libraryRepository.getOne(1));
        return newReport;
    }
}
