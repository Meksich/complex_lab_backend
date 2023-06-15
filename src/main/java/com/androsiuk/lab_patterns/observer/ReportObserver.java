package com.androsiuk.lab_patterns.observer;

import com.androsiuk.lab_patterns.entity.MonthlyReport;
import com.androsiuk.lab_patterns.repository.MonthlyReportRepository;
import lombok.AllArgsConstructor;
import java.util.Date;

@AllArgsConstructor
public class ReportObserver extends Observer{
    private MonthlyReportRepository monthlyReportRepository;
    private MonthlyReport monthlyReport;

    @Override
    public void update(Date returnDate, Double pledgeCost, Double userBalanceDeduction) {
        Date currDate = new Date();
        if(returnDate.after(currDate)){
            monthlyReport.setBooksIssued(monthlyReport.getBooksIssued() + 1);
        } else {
            monthlyReport.setLibraryIncome(monthlyReport.getLibraryIncome() + userBalanceDeduction);
        }
        if (monthlyReportRepository.existsById(monthlyReport.getId())){
            monthlyReportRepository.save(monthlyReport);
        }
    }
}