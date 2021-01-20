package com.example.demo.api;

import com.example.demo.model.projections.UserOrderView;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserOrdersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private OrderService orderService;
    private UserOrdersService userOrdersService;

    public OrderController(OrderService orderService, UserOrdersService userOrdersService) {
        this.orderService = orderService;
        this.userOrdersService = userOrdersService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "{id}")
    public void saveOrder(@PathVariable(value = "id") long personId) {
        this.orderService.save(personId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "{id}")
    public Iterable<UserOrderView> findOrderForPerson(@PathVariable(value = "id") long personId) {
        return this.userOrdersService.findUserOrderByPerson(personId);
    }
}
