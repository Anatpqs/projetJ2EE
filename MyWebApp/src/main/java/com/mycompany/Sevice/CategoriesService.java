package com.mycompany.Sevice;

import com.mycompany.Entity.Categories;
import com.mycompany.Repository.CategoriesRepository;
import com.mycompany.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired private CategoriesRepository repo;

    public List<Categories> listAll() {
        return (List<Categories>) repo.findAll();
    }

    public void save(Categories categories) {
        repo.save(categories);
    }

    public Categories get(Integer id) throws NotFoundException {
        Optional<Categories> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new NotFoundException("Could not find any categories with ID " + id);
    }

    public void delete(Integer id) throws NotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0) {
            throw new NotFoundException("Could not find any categories with ID " + id);
        }
        repo.deleteById(id);
    }
}
