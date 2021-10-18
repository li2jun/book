package com.dao.impl;

import com.bean.Book;
import com.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-09
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public List<Book> queryAllBooks() {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from `book`";
        List<Book> books = queryForList(sql, Book.class);
        return books;
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from `book` where id = ?";
        return queryForOne(sql,Book.class,id);
    }

    @Override
    public int updateBookById(Book book) {
        String sql = "update `book` set `name`=?, `price`=?,`author`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql, book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from `book` where id=?";
        return update(sql,id);
    }

    @Override
    public int addBook(Book book) {
        String sql = "insert into `book`(`name`,`price`,`author`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int queryPageTotalCount() {
        String sql = "select count(*) from `book`";
        Object o = queryForSingleValue(sql);
        Number total = (Number)o;
        return total.intValue();
    }

    @Override
    public List<Book> queryPageItems(int pageNO, int pageSize) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from `book` limit ?,?";
        List<Book> books = queryForList(sql, Book.class, (pageNO - 1) * pageSize, pageSize);
        return books;
    }

    @Override
    public List<Book> queryPageItemsByPrice(int pageNO, int pageSize,int min,int max) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from `book` where `price` between ? and ? order by `price`asc limit ?,?";
        List<Book> books = queryForList(sql, Book.class, min, max,(pageNO - 1) * pageSize,pageSize);
        return books;
    }

    @Override
    public int queryPageItemsByPriceCount(int min, int max) {
        String sql = "select count(*) from `book` where `price` between ? and ? ";
        Number total = (Number)queryForSingleValue(sql, min, max);
        return total.intValue();
    }

}
