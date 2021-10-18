package com.service.impl;

import com.bean.Book;
import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.service.BookService;

import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-09
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void updateBookById(Book book) {
        bookDao.updateBookById(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);

    }

    @Override
    public List<Book> queryBooks() {

        return bookDao.queryAllBooks();
    }

    @Override
    public void addBook(Book book) {

        bookDao.addBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
       return bookDao.queryBookById(id);
    }

    @Override
    public int queryPageTotalCount() {
        return bookDao.queryPageTotalCount();
    }

    @Override
    public List<Book> queryPageItems(int pageNO, int pageSize) {
        return bookDao.queryPageItems(pageNO, pageSize);
    }

    @Override
    public List<Book> queryPageItemsByPrice(int pageNO, int pageSize,int min,int max) {
        return bookDao.queryPageItemsByPrice(pageNO, pageSize, min, max);
    }

    @Override
    public int queryPageItemsByPriceCount(int min, int max) {
        return bookDao.queryPageItemsByPriceCount(min, max);
    }
}
