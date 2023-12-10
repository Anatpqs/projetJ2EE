package com.mycompany.Repository;

import com.mycompany.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {

    public Long countById(Long id);
}
