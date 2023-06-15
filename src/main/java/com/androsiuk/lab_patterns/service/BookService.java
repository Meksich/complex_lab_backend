package com.androsiuk.lab_patterns.service;

import com.androsiuk.lab_patterns.DTO.BookDTO;
import com.androsiuk.lab_patterns.entity.Book;
import com.androsiuk.lab_patterns.repository.BookRepository;
import com.androsiuk.lab_patterns.repository.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService extends com.androsiuk.lab_patterns.service.Service<Book> {
    private BookRepository bookRepository;
    private LibraryRepository libraryRepository;

    @Override
    public JpaRepository<Book, Integer> getRepository() {
        return bookRepository;
    }

    public Book transformDTO(BookDTO bookDTO){
        Book freshBook = new Book();
        freshBook.setReturnDate(bookDTO.getReturnDate());
        freshBook.setIsRented(bookDTO.getIsRented());
        freshBook.setAuthor(bookDTO.getAuthor());
        freshBook.setGenre(bookDTO.getGenre());
        freshBook.setLibrary(libraryRepository.getOne(bookDTO.getLibraryId()));
        freshBook.setInServiceSince(bookDTO.getInServiceSince());
        freshBook.setName(bookDTO.getName());
        freshBook.setPages(bookDTO.getPages());
        freshBook.setPledgeCost(bookDTO.getPledgeCost());
        freshBook.setRentalDefaultCost(bookDTO.getRentalDefaultCost());
        freshBook.setRentalExtraDayPrice(bookDTO.getRentalExtraDayPrice());
        create(freshBook);
        return freshBook;
    }
}
