package com.bobbbaich.dao;

import com.bobbbaich.Application;
import com.bobbbaich.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Bohdan Pohotilyi on 13.09.2016.
 */
public class BookDaoTest {
    private BookDao bookDao;
    private Book book;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        Sql2o sql2o = new Sql2o(Application.DATASOURCE,"","");
        connection = sql2o.open();

        bookDao = new BookDaoImpl(sql2o);
        book = newTestBook();
        
        bookDao.findAll().forEach(b -> bookDao.remove(b));
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    private Book newTestBook() {
        return new Book("test_name", "test_author");
    }

    @Test
    public void addingBookSetId() throws Exception {
        int originalBookId = book.getId();
        bookDao.add(book);
        assertNotEquals(originalBookId, book.getId());
    }

    @Test
    public void addedBookAreReturnedFromFindAll() throws Exception {
        bookDao.add(book);
        assertEquals(1, bookDao.findAll().size());
    }

    @Test
    public void noBooksReturnsEmptyList() throws Exception {
       assertEquals(0, bookDao.findAll().size());
    }
}