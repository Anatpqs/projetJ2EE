package com.mycompany.Sevice;

import com.mycompany.Entity.User;
import com.mycompany.Repository.UserRepository;
import com.mycompany.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired private UserRepository repo;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws NotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new NotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws NotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0) {
            throw new NotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(repo.findByEmail(email));
    }

    public Optional<User> findById(Integer id) {
        return repo.findById(id);
    }

}
