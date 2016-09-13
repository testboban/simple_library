package com.bobbbaich.handler;

import com.bobbbaich.Application;
import com.bobbbaich.dao.BookDao;
import com.bobbbaich.dao.BookDaoImpl;
import com.bobbbaich.model.Book;
import org.sql2o.Sql2o;

import java.util.List;

import static com.bobbbaich.utils.Console.getBookParam;

/**
 * Created by Bohdan Pohotilyi on 13.09.2016.
 */
public class BookHandler {
    private static final String NAME = "name";
    private static final String AUTHOR = "author";
    private static final String INDEX = "index";

    private BookDao bookDao;

    public BookHandler() {
        Sql2o sql2o = new Sql2o(Application.DATASOURCE,"","");
        bookDao = new BookDaoImpl(sql2o);
    }

    public void add() {
        Book book = new Book(getBookParam(NAME),getBookParam(AUTHOR));
        bookDao.add(book);
        System.out.println("Book " + book + " was added.");
    }
    public void remove(){
        List<Book> books = bookDao.getByName(getBookParam(NAME));
        Book book = null;
        if(books.isEmpty()){
            System.out.println("This book already deleted.");
        }

        if(books.size() == 1){
            book = books.get(0);
            bookDao.remove(book);
            System.out.println("Book " + book + "was removed.");
        }else if(books.size() > 1){
            int index = 1;
            for (Book b: books) {
                System.out.println(index++ + ". " + b.toString());
            }

            int indexToRemove = Integer.parseInt(getBookParam(INDEX));
            if(indexToRemove > 0 && indexToRemove < index){
                book = books.get(--indexToRemove);
                bookDao.remove(book);
                System.out.println("Book " + book + "was removed.");
            }
        }
    }

    public void edit(){
        List<Book> books = bookDao.getByName(getBookParam(NAME));
        Book book = null;
        if(books.isEmpty()){
            System.out.println("We don't have this book.");
        }

        if(books.size() == 1){
            bookDao.update(books.get(0));
            System.out.println("Book " + book + "was updated.");
        }else if(books.size() > 1){
            int index = 1;
            for (Book b: books) {
                System.out.println(index++ + ". " + b.toString());
            }

            int indexToRemove = Integer.parseInt(getBookParam(INDEX));
            if(indexToRemove > 0 && indexToRemove < index){
                book = books.get(--indexToRemove);
                book.setName(getBookParam(NAME));
                book.setAuthor(getBookParam(AUTHOR));
                bookDao.update(book);
                System.out.println("Book " + book + "was updated.");
            }
        }
    }

    public void all(){
        System.out.println("All our books: ");
        bookDao.findAll().forEach(System.out::println);
    }
}
