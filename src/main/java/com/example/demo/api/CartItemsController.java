package com.example.demo.api;

import com.example.demo.model.CartItems;
import com.example.demo.service.CartItemService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;

@RequestMapping("api/v1/cart")
@RestController
public class CartItemsController  {

    private CartItemService cartItemService;

    public CartItemsController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public void addToCart(@RequestBody CartItems cartItem) {
        cartItemService.save(cartItem);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "{id}")
    public Iterable<CartItems> getCartItems(@PathVariable(value = "id") long id) {
        return cartItemService.getCartItems(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/delete")
    public void deleteItem(@RequestBody CartItems cartItem) {
        cartItemService.deleteItem(cartItem);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        return "Person with that ID doesn't exist";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return "Invalid Request, Person ID should be a number";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return e.getLocalizedMessage();
    }
}
