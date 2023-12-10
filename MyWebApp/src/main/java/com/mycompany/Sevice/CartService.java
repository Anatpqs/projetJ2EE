package com.mycompany.Sevice;

import com.mycompany.Entity.CartItem;
import com.mycompany.Entity.Product;
import com.mycompany.Entity.User;
import com.mycompany.Repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    public void addToCart(User user, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(1); // vous pouvez ajuster la quantité selon vos besoins

        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(User user, Product product) {
        // Récupérez l'élément du panier correspondant à l'utilisateur et au produit
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);

        // Si l'élément du panier existe, supprimez-le
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
        }
    }

    public CartItem getCartItemByUserAndProduct(User user, Product product) {
        return cartItemRepository.findByUserAndProduct(user, product);
    }

    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public List<Product> getCartItems(User user) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        List<Product> productsInCart = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            productsInCart.add(cartItem.getProduct());
        }

        return productsInCart;
    }

    public double calculateTotalAmount(List<Product> cartItems) {
        double totalAmount = 0.0;

        for (Product cartItem : cartItems) {
            totalAmount += cartItem.getPrice();
        }

        return totalAmount;
    }

    public void clearCart(User user) {
        // Retrieve the cart items associated with the user
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        // Delete each cart item
        for (CartItem cartItem : cartItems) {
            cartItemRepository.delete(cartItem);
        }
    }

}
