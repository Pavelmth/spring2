package com.geekbrains.septembermarket.repositories;

import com.geekbrains.septembermarket.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
