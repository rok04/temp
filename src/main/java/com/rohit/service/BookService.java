package com.rohit.service;

import com.rohit.exception.NotFoundException;
import com.rohit.model.Book;
import com.rohit.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

        bookRepository.deleteById(book.getId());
    }

}
