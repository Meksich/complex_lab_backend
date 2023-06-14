package com.androsiuk.lab_patterns.observer;

import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.repository.BookRepository;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class BookObserver extends Observer{
    private BookRepository bookRepository;
    private Book book;

    @Override
    public void update(Date returnDate, Double pledgeCost, Double userBalanceDeduction) {
        Date currDate = new Date();
        if(returnDate.after(currDate)){
            book.setIsRented(true);
            book.setReturnDate(returnDate);
        } else {
            book.setIsRented(false);
            book.setReturnDate(null);
        }
        if (bookRepository.existsById(book.getId())){
            bookRepository.save(book);
        }
    }
}
