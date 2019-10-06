package com.geekbrains.septembermarket.repositories;

import com.geekbrains.septembermarket.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
