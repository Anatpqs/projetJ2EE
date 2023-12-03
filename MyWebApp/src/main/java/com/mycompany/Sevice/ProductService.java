package com.mycompany.Sevice;

import com.mycompany.Entity.Product;
import com.mycompany.Entity.User;
import com.mycompany.Repository.ProductRepository;
import com.mycompany.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;

    public List<Product> listAll() {
        return (List<Product>) repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(Long id) throws NotFoundException {
        Optional<Product> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new NotFoundException("Could not find any product with ID " + id);
    }

    public void delete(Long id) throws NotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0) {
            throw new NotFoundException("Could not find any product with ID " + id);
        }
        repo.deleteById(id);
    }

    public Product getProduct(Long id) throws NotFoundException {
        Optional<Product> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new NotFoundException("Could not find any product with ID " + id);
    }

    public Product getProductById(Long id) throws NotFoundException {
        Optional<Product> productOptional = repo.findById(id);
        return productOptional.orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
    }


}
