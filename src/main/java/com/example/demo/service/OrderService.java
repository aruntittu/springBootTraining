package com.example.demo.service;

import com.example.demo.dao.OrderItemsDao;
import com.example.demo.model.CartItems;
import com.example.demo.model.OrderItems;
import com.example.demo.model.Person;
import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.OrderDetailsView;
import com.example.demo.repository.CartItemsRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserOrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderItemsDao {

    private OrderRepository orderRepository;
    private CartItemsRepository cartItemsRepository;
    private UserOrdersRepository userOrdersRepository;


    public OrderService(OrderRepository orderRepository, CartItemsRepository cartItemsRepository, UserOrdersRepository userOrdersRepository) {
        this.orderRepository = orderRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.userOrdersRepository = userOrdersRepository;
    }

    @Override
    public void save(long personId) {
        Person person = new Person();
        person.setId(personId);
        Iterable<CartItems> cartItems = this.cartItemsRepository.findCartItemsByPerson(person);
        UserOrders userOrder = new UserOrders(person);
        UserOrders savedUserOrder = this.userOrdersRepository.save(userOrder);
        for (CartItems cartItem : cartItems) {
            OrderItems orderItem = new OrderItems(cartItem.getProduct(), cartItem.getQuantity(), savedUserOrder);
            OrderItems savedOrderItem = this.orderRepository.save(orderItem);
        }
        this.cartItemsRepository.deleteByPerson(person);
    }

    @Override
    public Iterable<OrderDetailsView> getOrderDetails(UserOrders userOrder) {
       return this.orderRepository.findOrderItemsByUserOrder(userOrder);
    }
}
