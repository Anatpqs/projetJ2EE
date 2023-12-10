package com.mycompany.Controller;

import com.mycompany.Entity.*;
import com.mycompany.Sevice.CartService;
import com.mycompany.Sevice.OrderService;
import com.mycompany.Sevice.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/admin/removeOrder")
    public String removeOrder(@RequestParam Long orderId) {
        orderService.removeOrder(orderId);
        return "redirect:/admin/orders";
    }

    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderService.getOrdersWithOrderProducts();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/processPayment")
    public String processPayment(Model model, PaymentForm paymentForm, HttpSession session) {
        // Retrieve the user ID from the session
        Integer userId = (Integer) session.getAttribute("userId");

        // Check if the user is logged in
        if (userId != null) {
            // Retrieve the user based on the user ID
            Optional<User> userOptional = userService.findById(userId);

            // Check if the user exists
            if (userOptional.isPresent()) {
                User user = userOptional.get();

                // Retrieve cart items for the user
                List<Product> cartItems = cartService.getCartItems(user);

                // Create an order for the user
                Order order = orderService.saveOrder(user, cartItems);

                // Perform payment processing logic here using paymentForm details
                // For demonstration purposes, let's assume the payment is successful
                model.addAttribute("message", "Payment successful!");

                // Clear the user's cart after a successful payment
                cartService.clearCart(user);

                // Add the order details to the model
                model.addAttribute("order", order);

                // Redirect to a payment success page
                return "paymentSuccess";
            } else {
                // Handle the case where the user with the given ID is not found
                return "redirect:/login"; // or an appropriate error page
            }
        } else {
            // The user is not logged in, redirect them to the login page
            return "redirect:/login";
        }
    }
}
