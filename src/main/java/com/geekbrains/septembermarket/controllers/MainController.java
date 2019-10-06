package com.geekbrains.septembermarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        try{
            if (request.getUserPrincipal().getName() != null) {
                model.addAttribute("userName", request.getUserPrincipal().getName());
            }
        } catch (NullPointerException e) {

        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "Hello";
    }
}
