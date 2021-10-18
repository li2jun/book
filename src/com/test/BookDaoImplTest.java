package com.test;

import com.bean.Book;
import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ahuthor lzy
 * @create 2021-10-09
 */
public class BookDaoImplTest {
    BookDao bd = new BookDaoImpl();
    @Test
    public void queryAllBooks() {
        List<Book> books = bd.queryAllBooks();
        System.out.println(books);
        for(Book book: books){
            System.out.println(book);
        }
    }

    @Test
    public void queryBookById() {
        Book book = bd.queryBookById(1);
        System.out.println(book);
    }

    @Test
    public void updateBookById() {
        Book book = new Book(2,"算法", new BigDecimal("66.66"),"左神",100,50,null);
        System.out.println(bd.updateBookById(book));
    }

    @Test
    public void deleteBookById() {
        System.out.println(bd.deleteBookById(3));
    }

    @Test
    public void addBook() {
        Book book = new Book(null,"算法", new BigDecimal("66.66"),"左神",100,50,null);
        System.out.println(bd.addBook(book));
    }
    @Test
    public void queryPageTotalCount(){
        System.out.println(bd.queryPageTotalCount());
    }
}