package com.bobbbaich.dao;

import com.bobbbaich.model.Book;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by Bohdan Pohotilyi on 13.09.2016.
 */
public class BookDaoImpl implements BookDao {
    private final Sql2o sql2o;

    public BookDaoImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Book book){
        String query = "insert into books(name, author) values (:name, :author)";
        try(Connection connection = sql2o.open()){
            int id = (int)connection.createQuery(query)
                    .bind(book)
                    .executeUpdate()
                    .getKey();
            book.setId(id);
        }
    }

    @Override
    public void remove(Book book) {
        try(Connection connection = sql2o.open()){
            connection.createQuery("delete from books where id = :id")
                    .addParameter("id", book.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void update(Book book) {
        try(Connection connection = sql2o.open()){
            connection.createQuery("update books set name = :name, author = :author where id = :id")
                    .addParameter("id", book.getId())
                    .addParameter("name", book.getName())
                    .addParameter("author", book.getAuthor())
                    .executeUpdate();
        }
    }

    @Override
    public List<Book> findAll() {
        try(Connection connection = sql2o.open()){
            return connection.createQuery("select * from books order by name")
                    .executeAndFetch(Book.class);
        }
    }

    @Override
    public List<Book> getByName(String name) {
        try(Connection connection = sql2o.open()){
                return connection.createQuery("select * from books where name = :name")
                        .addParameter("name", name)
                        .executeAndFetch(Book.class);
        }
    }
}

