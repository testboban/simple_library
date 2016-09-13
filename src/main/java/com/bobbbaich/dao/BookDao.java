package com.bobbbaich.dao;

import com.bobbbaich.model.Book;

import java.util.List;

/**
 * Created by Bohdan Pohotilyi on 13.09.2016.
 */
public interface BookDao {
    void add(Book book);
    void remove(Book book);
    void update(Book book);
    List<Book> findAll();
    List<Book> getByName(String name);
}
