package com.learning.bookstore.controllers;

import com.learning.bookstore.dtos.Book;
import com.learning.bookstore.services.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class BookStoreController {

    @Autowired
    private BookDAO bookDAO;
    @GetMapping("/book/{bookName}")
    public Book findBook(@PathVariable String bookName){
        sleepGracefully(5000);
        return bookDAO.findBookByName(bookName)
                .orElseThrow(() -> new IllegalArgumentException("Book name "+bookName+" not found."));
    }

    private void sleepGracefully(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

        }
    }
}
