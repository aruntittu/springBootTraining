package com.example.demo.service;

import com.example.demo.dao.CartItemsDao;
import com.example.demo.model.CartItems;
import com.example.demo.model.projections.CartItemsIdAndQuantity;
import com.example.demo.repository.CartItemsRepository;
import org.springframework.stereotype.Service;


@Service
public class CartItemService implements CartItemsDao {

    private CartItemsRepository cartItemsRepository;

    public CartItemService(CartItemsRepository cartItemsRepository) {
        this.cartItemsRepository = cartItemsRepository;
    }

    @Override
    public void save(CartItems cartItem) {
        CartItemsIdAndQuantity existingCartItem = cartItemsRepository.findCartItemsByPersonAndProduct(cartItem.getPerson(), cartItem.getProduct());
        if(existingCartItem!=null) {
           int quantity = existingCartItem.getQuantity() + cartItem.getQuantity();
           if(cartItemsRepository.findById(existingCartItem.getId()).isPresent()) {
               CartItems cartItems1 = cartItemsRepository.findById(existingCartItem.getId()).get();
               cartItems1.setId(existingCartItem.getId());
               cartItems1.setQuantity(quantity);
               cartItemsRepository.save(cartItems1);
           }
        } else {
            cartItemsRepository.save(cartItem);
        }
    }

    @Override
    public Iterable<CartItems> getCartItems(long id) {
        return cartItemsRepository.findCartItemsByPersonId(id);
    }

    @Override
    public void deleteItem(CartItems cartItem) {
        long personId = cartItem.getPerson().getId();
        int productId = cartItem.getProduct().getId();
        cartItemsRepository.deleteItemByPersonAndProduct(productId, personId);
    }
}
