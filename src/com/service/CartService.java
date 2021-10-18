package com.service;

import com.bean.Book;
import com.bean.Cart;
import com.bean.CartItems;

/**
 * @ahuthor lzy
 * @create 2021-10-13
 */
public interface CartService {
   public void addToCart(Cart cart, Integer id);
   public void delete(Cart cart,int id);
   public void emptyCart(Cart cart);
}
