package org.bookstore.bookstore.repository;


import org.bookstore.bookstore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
