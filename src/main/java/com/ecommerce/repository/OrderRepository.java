package com.ecommerce.repository;


import com.ecommerce.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<CustomerOrder,Long> {

}