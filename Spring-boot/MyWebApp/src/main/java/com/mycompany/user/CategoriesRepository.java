package com.mycompany.user;

import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories,Integer> {

    public Long countById(Integer id);
}
