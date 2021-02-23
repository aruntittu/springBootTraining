package com.example.demo.service;

import com.example.demo.dao.CartItemsDao;
import com.example.demo.model.CartItems;
import com.example.demo.model.projections.CartItemsIdAndQuantity;
import com.example.demo.repository.CartItemsRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class CartItemService implements CartItemsDao {

    private CartItemsRepository cartItemsRepository;
    private PersonRepository personRepository;

    public CartItemService(CartItemsRepository cartItemsRepository, PersonRepository personRepository) {
        this.cartItemsRepository = cartItemsRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void save(CartItems cartItem) {
        if(cartItem.getQuantity()!=null && cartItem.getPerson()!=null && cartItem.getProduct()!=null) {
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
        } else {
            throw new DataIntegrityViolationException("Invalid Request, Check properties of the request");
        }

    }

    @Override
    public Iterable<CartItems> getCartItems(long person_id) {
        if(personRepository.findById(person_id).isPresent()) {
            return cartItemsRepository.findCartItemsByPersonId(person_id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deleteItem(CartItems cartItem) {
        if(cartItem.getPerson()!=null && cartItem.getProduct()!=null) {
            long personId = cartItem.getPerson().getId();
            int productId = cartItem.getProduct().getId();
            cartItemsRepository.deleteItemByPersonAndProduct(productId, personId);
        } else {
            throw new DataIntegrityViolationException("Invalid Request, Check properties of the request");
        }
    }
}
