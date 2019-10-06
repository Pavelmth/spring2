package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Order;
import com.geekbrains.septembermarket.entities.User;
import com.geekbrains.septembermarket.repositories.UserRepository;
import com.geekbrains.septembermarket.services.MailService;
import com.geekbrains.septembermarket.services.OrderService;
import com.geekbrains.septembermarket.services.UserService;
import com.geekbrains.septembermarket.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private MailService mailService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/create")
    public String createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.createOrder(user);
        mailService.sendOrderMail(order);
        return "redirect:/shop";
    }
}
