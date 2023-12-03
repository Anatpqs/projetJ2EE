package com.mycompany.Repository;

import com.mycompany.Entity.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories,Integer> {

    public Long countById(Integer id);
}
