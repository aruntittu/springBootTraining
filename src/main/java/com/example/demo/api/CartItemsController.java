package com.example.demo.api;

import com.example.demo.model.CartItems;
import com.example.demo.service.CartItemService;
import org.springframework.web.bind.annotation.*;

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
        this.cartItemService.save(cartItem);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "{id}")
    public Iterable<CartItems> getCartItems(@PathVariable(value = "id") long id) {
        return this.cartItemService.getCartItems(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/delete")
    public void deleteItem(@RequestBody CartItems cartItem) {
        this.cartItemService.deleteItem(cartItem);
    }
}
