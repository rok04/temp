package com.rohit.controller;

import com.rohit.model.Book;
import com.rohit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        System.out.println("HI There ");
        Book book = new Book("123131", "Harry Potter", "12313617", "I am Harry Potter");
        Book book2 = new Book("123131", "Harry Potter", "12313617", "I am Harry Potter");

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        model.addAttribute("books", books);
        return "list-books";
    }

    @RequestMapping("/add-book")
    public String createBook(Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }

        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String showCreateForm(Book book, Model model) {
        System.out.println("showCreateForm");
//        model.addAttribute("categories", categoryService.findAllCategories());
//        model.addAttribute("authors", authorService.findAllAuthors());
//        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "add-book";
    }
}
