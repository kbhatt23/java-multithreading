package com.learning.bookstore.services;

import com.learning.bookstore.dtos.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Profile("RACHNA")
public class RachnaBookDao implements BookDAO{

    private Map<String,Book> bookStore;

    @Value("${book.store.name}")
    private String storeName;

    @PostConstruct
    public void init(){
        bookStore = new HashMap<>();
        bookStore.put("And Then There Were None",
                new Book(
                        storeName, "And Then There Were None", "Agatha Christie", 10, 300,
                        bookUrl("And Then There Were None")));

        bookStore.put("A Study in Scarlet",
                new Book(
                        storeName, "A Study in Scarlet", "Arthur Conan Doyle", 10, 108,
                        bookUrl("A Study in Scarlet")));

        bookStore.put("The Day of the Jackal",
                new Book(
                        storeName, "The Day of the Jackal", "Frederick Forsyth", 11, 464,
                        bookUrl("The Day of the Jackal")));

        bookStore.put("The Wisdom of Father Brown",
                new Book(storeName, "The Wisdom of Father Brown", "G.K. Chesterton", 7, 136,
                        bookUrl("The Wisdom of Father Brown")));

        bookStore.put("The Poet",
                new Book(storeName, "The Poet", "Michael Connelly", 15, 528,
                        bookUrl("The Poet")));
    }

    private String bookUrl(String name) {
        try{
            return String.format("https://%s.com/store/book?name=%s",
                    storeName.toLowerCase(),name);
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public Optional<Book> findBookByName(String name) {
        return Optional.ofNullable(bookStore.get(name));
    }
}
