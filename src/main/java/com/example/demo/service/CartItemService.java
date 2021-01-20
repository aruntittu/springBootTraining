package com.example.demo.service;

import com.example.demo.dao.CartItemsDao;
import com.example.demo.model.CartItems;
import com.example.demo.repository.CartItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartItemService implements CartItemsDao {

    private CartItemsRepository cartItemsRepository;

    public CartItemService(CartItemsRepository cartItemsRepository) {
        this.cartItemsRepository = cartItemsRepository;
    }

    @Override
    public void save(CartItems cartItem) {
        List<Object[]> prevCartItem = this.cartItemsRepository.findCartItemsByPersonAndProduct(cartItem.getProduct().getId(), cartItem.getPerson().getId());
        if(!prevCartItem.isEmpty()) {
            Object[] lineItem = prevCartItem.get(0);
            Optional<CartItems> existingCartItem = this.cartItemsRepository.findById((long) lineItem[0]);
            existingCartItem.get().setId((long) lineItem[0]);
            int quantity = (int) lineItem[1] + cartItem.getQuantity();
            existingCartItem.get().setQuantity(quantity);
            this.cartItemsRepository.save(existingCartItem.get());
        } else {
            this.cartItemsRepository.save(cartItem);
        }
    }

    @Override
    public Iterable<CartItems> getCartItems(long id) {
        return this.cartItemsRepository.findCartItemsByPersonId(id);
    }

    @Override
    public void deleteItem(CartItems cartItem) {
        long personId = cartItem.getPerson().getId();
        int productId = cartItem.getProduct().getId();
        this.cartItemsRepository.deleteItemByPersonAndProduct(productId, personId);
    }
}
