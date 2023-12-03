package com.mycompany.user;

import com.mycompany.Entity.User;
import com.mycompany.Exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> listAll();

    void save(User user);

    User get(Integer id) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

    //ser registerUser(RegistrationRequest request);

    Optional<User> findByEmail(String email);
}
