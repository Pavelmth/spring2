package com.geekbrains.septembermarket.repositories;

import com.geekbrains.septembermarket.entities.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
