package com.mycompany.Controller;

import com.mycompany.Entity.*;
import com.mycompany.Sevice.CartService;
import com.mycompany.Sevice.ProductService;
import com.mycompany.Sevice.UserService;
import com.mycompany.Exception.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Optional;


@Controller
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam Long productId, HttpSession session) throws NotFoundException {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            Optional<User> userOptional = userService.findById(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Product product = productService.getProductById(productId);

                // Check if the product is already in the user's cart
                CartItem existingCartItem = cartService.getCartItemByUserAndProduct(user, product);

                if (existingCartItem != null) {
                    existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
                    cartService.saveCartItem(existingCartItem);
                } else {
                    // Product is not in the cart, add it
                    cartService.addToCart(user, product);
                }

                return "redirect:/homeLogin"; // or the page you want to display after adding to the cart
            } else {
                // Handle the case where the user with the given ID is not found
                return "redirect:/login"; // or an appropriate error page
            }
        } else {
            // The user is not logged in, redirect them to the login page
            return "redirect:/login";
        }
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            Optional<User> userOptional = userService.findById(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                model.addAttribute("user", user);
                return "cart";
            }
        }

        // Si l'utilisateur n'est pas connecté ou si quelque chose d'autre ne va pas,
        // vous pouvez rediriger l'utilisateur vers la page de connexion ou une autre page.
        return "redirect:/login";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) throws NotFoundException {
        // Récupérer l'utilisateur depuis la session
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            Optional<User> userOptional = userService.findById(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Product product = productService.getProductById(productId);

                // Appeler le service pour supprimer le produit du panier
                cartService.removeFromCart(user, product);
            }
        }

        // Rediriger vers la page du panier
        return "redirect:/cart";
    }
}
