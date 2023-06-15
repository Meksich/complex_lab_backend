package com.androsiuk.lab_patterns.processing;

import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.entity.User;
import com.androsiuk.lab_patterns.strategy.Context;
import com.androsiuk.lab_patterns.strategy.NoPenaltyStrategy;
import com.androsiuk.lab_patterns.strategy.PenaltyStrategy;
import lombok.AllArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@AllArgsConstructor
public class OrderProcessing {
    public static Context context;

    public static double deductRentalCost(User user, Book book,  Date issueDate, Date expectedReturnDate, double damagePenalty) {
        Date returnDate = new Date();
        double userBalanceDeduction ;

        if ((damagePenalty > 0) || (returnDate.after(expectedReturnDate)) || (TimeUnit.DAYS.convert(returnDate.getTime() - issueDate.getTime(), TimeUnit.MILLISECONDS) >= 90)) {
            context = new Context(new PenaltyStrategy(damagePenalty));
        } else {
            context = new Context(new NoPenaltyStrategy(user));
        }

        if ( ChronoUnit.DAYS.between(issueDate.toInstant(), returnDate.toInstant()) <= 30){
             userBalanceDeduction = book.getRentalDefaultCost();
        } else {
               long extraPeriod = ChronoUnit.DAYS.between(issueDate.toInstant(), returnDate.toInstant()) - 30;
               userBalanceDeduction = book.getRentalDefaultCost() + book.getRentalExtraDayPrice() * extraPeriod;
           }

        return context.executeStrategy(userBalanceDeduction);
    }
}
