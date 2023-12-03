package com.mycompany.Repository;

import com.mycompany.Entity.Order;
import com.mycompany.Entity.Product;
import com.mycompany.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.orderProducts")
    List<Order> findAllWithOrderProducts();
}
