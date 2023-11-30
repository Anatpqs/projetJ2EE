package com.mycompany.user;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {

    public Long countById(Long id);
}
