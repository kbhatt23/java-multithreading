package com.learning.bookstore.dtos;

public record Book(String bookStore, String bookName, String author, int cost,
                   int numPages, String link) {

}
