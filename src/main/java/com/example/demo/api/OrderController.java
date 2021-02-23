package com.example.demo.api;

import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.OrderDetailsView;
import com.example.demo.model.projections.UserOrderView;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserOrdersService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;

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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "orderDetails/{id}")
    public Iterable<OrderDetailsView> findOrderDetails(@PathVariable(value = "id") long userOrderId) {
        UserOrders userOrder = new UserOrders();
        userOrder.setId(userOrderId);
        return this.orderService.getOrderDetails(userOrder);
    }

    //Stored Procedure
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "orderDetails/total/{id}")
    public int totalOrdersByPerson_StoredProcedure(@PathVariable(value = "id") long person_id) {
        return this.userOrdersService.totalOrders(person_id);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleEntityExistsException(MethodArgumentTypeMismatchException e) {
        return "Invalid Request, Person ID should be a number";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Throwable cause = ((DataIntegrityViolationException) e).getRootCause();
        if (cause instanceof SQLIntegrityConstraintViolationException) {
            return "Cannot save order as person with that ID doesn't exist.";
        } else {
            return e.getLocalizedMessage();
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        return "Users doesn't have products in cart.";
    }

}
