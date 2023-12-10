package com.mycompany.Repository;

import com.mycompany.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Integer> {

    public Long countById(Integer id);
}
