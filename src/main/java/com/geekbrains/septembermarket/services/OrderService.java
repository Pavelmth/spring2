package com.geekbrains.septembermarket.services;

import com.geekbrains.septembermarket.entities.Order;
import com.geekbrains.septembermarket.entities.User;
import com.geekbrains.septembermarket.repositories.OrderRepository;
import com.geekbrains.septembermarket.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    private Cart cart;

    @Autowired
    private UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, Cart cart) {
        this.orderRepository = orderRepository;
        this.cart = cart;
    }

    public Order createOrder(User user) {
        Order order = new Order(user);
        cart.getItems().values().stream().forEach(i -> order.addItem(i));
        cart.clear();
        return orderRepository.save(order);
    }
}
