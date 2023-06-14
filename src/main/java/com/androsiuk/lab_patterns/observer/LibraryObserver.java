package com.androsiuk.lab_patterns.observer;

import com.androsiuk.lab_patterns.entity.Library;
import com.androsiuk.lab_patterns.repository.LibraryRepository;
import lombok.AllArgsConstructor;

import java.util.Date;


@AllArgsConstructor
public class LibraryObserver extends Observer{
    private LibraryRepository libraryRepository;
    private Library library;

    @Override
    public void update(Date returnDate, Double pledgeCost, Double userBalanceDeduction) {
        Date currDate = new Date();
        if(returnDate.after(currDate)){
            library.setBalanceUaH(library.getBalanceUaH() + pledgeCost);
            library.setBooksInRent(library.getBooksInRent() + 1);
        } else {
            library.setBalanceUaH(library.getBalanceUaH() - pledgeCost + userBalanceDeduction);
            library.setBooksInRent(library.getBooksInRent() - 1);
        }
        if (libraryRepository.existsById(library.getId())){
            libraryRepository.save(library);
        }
    }
}
