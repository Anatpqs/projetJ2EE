package com.mycompany.Repository;

import com.mycompany.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    public Long countById(Integer id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String username);
}
