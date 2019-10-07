package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Order;
import com.geekbrains.septembermarket.entities.User;
import com.geekbrains.septembermarket.repositories.UserRepository;
import com.geekbrains.septembermarket.services.MailService;
import com.geekbrains.septembermarket.services.OrderService;
import com.geekbrains.septembermarket.services.ProductsService;
import com.geekbrains.septembermarket.services.UserService;
import com.geekbrains.septembermarket.utils.Cart;
import com.geekbrains.septembermarket.utils.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
//    private MailService mailService;

    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

//    public void setMailService(MailService mailService) {
//        this.mailService = mailService;
//    }

    @GetMapping()
    public String showOrderForm(Model model, HttpServletRequest request) {
        try{
            if (request.getUserPrincipal().getName() != null) {
                model.addAttribute("userName", request.getUserPrincipal().getName());
            }
        } catch (NullPointerException e) {

        }

        model.addAttribute("items", cart.getItems().values());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "order_form";
    }

    @GetMapping("/create")
    public String createOrder(HttpServletRequest request,
                              @RequestParam(name = "address", required = false) String address
    ) {
        User user = userService.findByUsername(request.getUserPrincipal().getName());
        Order order = orderService.createOrder(user);
//        mailService.sendOrderMail(order);
        // адрес доставки в дольнейшем добавить в заказ.
        return "redirect:/shop";
    }

    @PostMapping("/create")
    public String createOrderAnonymous(HttpServletRequest request,
                                       @RequestParam(name = "name", required = false) String name,
                                       @RequestParam(name = "phone") String phone,
                                       @RequestParam(name = "address") String address
    ) {
        //Создаем урезанную версию юзера.
        SystemUser systemUser = new SystemUser();
        if (name != null && !name.isEmpty()) {
            systemUser.setUsername(name);
        } else {
            systemUser.setUsername("system");
        }
        systemUser.setEmail("system@system.com");
        systemUser.setFirstName("system");
        systemUser.setLastName("system");
        systemUser.setPhone(phone);
        systemUser.setPassword("eqwrgvravrfvarev");
        User user = userService.save(systemUser);
        Order order = orderService.createOrder(user);
//        mailService.sendOrderMail(order);
        // адрес доставки в дольнейшем добавить в заказ.
        return "redirect:/shop";
    }
}
