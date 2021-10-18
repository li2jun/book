package com.service;

import com.bean.Book;

import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-09
 */
public interface BookService {
    public void updateBookById(Book book);
    public void deleteBookById(Integer id);
    public List<Book> queryBooks();
    public void addBook(Book book);
    public Book queryBookById(Integer id);
    public int queryPageTotalCount();
    public List<Book> queryPageItems(int pageNO,int pageSize);
    public List<Book> queryPageItemsByPrice(int pageNO, int pageSize,int min,int max);
    public int queryPageItemsByPriceCount(int min,int max);
}
