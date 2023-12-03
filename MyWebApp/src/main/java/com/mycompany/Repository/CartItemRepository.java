package com.mycompany.Repository;

import com.mycompany.Entity.CartItem;
import com.mycompany.Entity.Product;
import com.mycompany.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
    // Vous pouvez ajouter des méthodes personnalisées pour la manipulation du panier si nécessaire
    List<CartItem> findByUser(User user);
}
