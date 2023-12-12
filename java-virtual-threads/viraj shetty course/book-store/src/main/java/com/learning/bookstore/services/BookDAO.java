package com.learning.bookstore.services;

import com.learning.bookstore.dtos.Book;

import java.util.Optional;

public interface BookDAO {

    public Optional<Book> findBookByName(String name);
}
