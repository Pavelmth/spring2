package com.geekbrains.septembermarket.utils;

import com.geekbrains.septembermarket.entities.OrderItem;
import com.geekbrains.septembermarket.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private Map<Long, OrderItem> items;
    private BigDecimal totalPrice;

    public Map<Long, OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @PostConstruct
    public void init() {
        items = new LinkedHashMap<>();
    }

    public void addProduct(Product product) {
        OrderItem item = items.get(product.getId());
        if (item == null) {
            item = new OrderItem();
            item.setItemPrice(product.getPrice());
            item.setProduct(product);
            item.setQuantity(0);
        }
        item.setQuantity(item.getQuantity() + 1);
        item.setTotalPrice(item.getItemPrice().multiply(new BigDecimal(item.getQuantity())));
        items.put(product.getId(), item);
        recalculate();
    }

    public void removeItem(Product product) {
        items.remove(product.getId());
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = new BigDecimal(0);
    }

    private void recalculate() {
        totalPrice = new BigDecimal(0);
        items.values().stream().forEach(oi -> totalPrice = totalPrice.add(oi.getTotalPrice()));
    }
}
