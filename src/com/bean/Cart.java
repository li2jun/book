package com.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-13
 */
public class Cart {
    private List<CartItems> items = new ArrayList<CartItems>();

    public Cart() {
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(CartItems item: items){
            if(item != null){
                totalCount +=item.getCount();
            }
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("0");
        for(CartItems item:items){
            BigDecimal price = item.getTotalPrice();
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }

    public List<CartItems> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public void setItems(List<CartItems> items) {
        this.items = items;
    }
}
