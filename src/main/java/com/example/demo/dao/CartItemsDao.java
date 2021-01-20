package com.example.demo.dao;

import com.example.demo.model.CartItems;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CartItemsDao{

    void save(CartItems cartItems) ;

    Iterable<CartItems> getCartItems(long id);

    void deleteItem(CartItems cartItem);
}