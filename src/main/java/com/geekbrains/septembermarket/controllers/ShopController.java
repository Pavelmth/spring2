package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Product;
import com.geekbrains.septembermarket.services.ProductsService;
import com.geekbrains.septembermarket.utils.ProductsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String shop(Model model, HttpServletRequest request, HttpServletResponse response,
                       @CookieValue(value = "page_size", required = false) Integer pageSize,
                       @RequestParam(name = "pageNumber", required = false) Integer pageNumber
                       // @RequestParam Map<String, String> params
    ) {
        ProductsFilter productsFilter = new ProductsFilter(request);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
            response.addCookie(new Cookie("page_size", String.valueOf(pageSize)));
        }
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("filters", productsFilter.getFiltersString());
        Page<Product> page = productsService.findAllByPagingAndFiltering(productsFilter.getSpecification(), PageRequest.of(pageNumber - 1, 10, Sort.Direction.ASC, "id"));
        model.addAttribute("page", page);
        return "shop";
    }
}
