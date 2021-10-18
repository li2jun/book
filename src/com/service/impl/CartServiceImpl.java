package com.service.impl;

import com.bean.Book;
import com.bean.Cart;
import com.bean.CartItems;
import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.service.CartService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-13
 */
public class CartServiceImpl implements CartService {
    BookDao bookDao = new BookDaoImpl();
    @Override
    //先判断该图书在不在list集合，若在数量加1，若不在添加进新的list中
    public void addToCart(Cart cart, Integer id) {
        Book book = bookDao.queryBookById(id);
        List<CartItems> items = cart.getItems();
        for(CartItems item:items){
            if(item.getId() == id){
                item.setCount(item.getCount()+ 1);
                item.setTotalPrice(item.getPrice().multiply(new BigDecimal((item.getCount()+ 1) + "")));
                return;
            }else{
                continue;
            }
        }
        items.add(new CartItems(id,book.getName(),1,book.getPrice(),book.getPrice()));
    }

    @Override
    public void delete(Cart cart,int id) {
        List<CartItems> items = cart.getItems();
        int index = 0;
        for(CartItems item:items){
            if(item.getId() == id){
                break;
            }else{
                index+=1;
            }
        }
        items.remove(index);
    }

    @Override
    public void emptyCart(Cart cart) {
        List<CartItems> items = cart.getItems();
        items.clear();
    }
}
