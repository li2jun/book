package com.dao;

import com.bean.Book;

import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-09
 */
public interface BookDao {
     public List<Book> queryAllBooks();
     public Book queryBookById(Integer id);
     public int updateBookById(Book book);
     public int deleteBookById(Integer id);
     public int addBook(Book book);
     public int queryPageTotalCount();
     public List<Book> queryPageItems(int pageNO, int pageSize);
     public List<Book> queryPageItemsByPrice(int pageNO, int pageSize,int min,int max);
     public int queryPageItemsByPriceCount(int min,int max);
}
