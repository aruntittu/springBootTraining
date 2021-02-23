package com.example.demo.service;

import com.example.demo.dao.OrderItemsDao;
import com.example.demo.model.CartItems;
import com.example.demo.model.OrderItems;
import com.example.demo.model.Person;
import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.OrderDetailsView;
import com.example.demo.repository.CartItemsRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.UserOrdersRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OrderService implements OrderItemsDao {

    private OrderRepository orderRepository;
    private CartItemsRepository cartItemsRepository;
    private UserOrdersRepository userOrdersRepository;
    private PersonRepository personRepository;
    
    public OrderService(OrderRepository orderRepository, CartItemsRepository cartItemsRepository, UserOrdersRepository userOrdersRepository, PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.userOrdersRepository = userOrdersRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void save(long personId) {
        if (personRepository.findById(personId).isPresent()) {
            Person person = new Person();
            person.setId(personId);
            Iterable<CartItems> cartItems = cartItemsRepository.findCartItemsByPerson(person);
            if (cartItems.iterator().hasNext()) {
                UserOrders userOrder = new UserOrders(person);
                UserOrders savedUserOrder = userOrdersRepository.save(userOrder);
                for (CartItems cartItem : cartItems) {
                    OrderItems orderItem = new OrderItems(cartItem.getProduct(), cartItem.getQuantity(), savedUserOrder);
                    OrderItems savedOrderItem = orderRepository.save(orderItem);
                }
                this.cartItemsRepository.deleteByPerson(person);
            } else {
                throw new EntityNotFoundException();
            }
        } else {
            throw new DataIntegrityViolationException("User doesnt exist with that ID");
        }
    }

    @Override
    public Iterable<OrderDetailsView> getOrderDetails(UserOrders userOrder) {
       return this.orderRepository.findOrderItemsByUserOrder(userOrder);
    }
}
