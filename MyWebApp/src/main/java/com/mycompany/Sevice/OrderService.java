package com.mycompany.Sevice;

import com.mycompany.Entity.Order;
import com.mycompany.Entity.OrderProduct;
import com.mycompany.Entity.Product;
import com.mycompany.Entity.User;
import com.mycompany.Entity.CartItem;
import com.mycompany.Repository.CartItemRepository;
import com.mycompany.Repository.OrderProductRepository;
import com.mycompany.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private CartService cartService;

    @Autowired
    private OrderProductRepository orderProductRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    Order addOrderItem(Order order, Product product, int quantity) {
        return null;
    }

    List<Order> getOrdersWithOrderItems() {
        return null;
    }


    public Order saveOrder(User user, List<Product> products) {
        Order order = new Order();
        order.setUser(user);

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (Product product : products) {
            // Récupérez le CartItem associé à l'utilisateur et au produit
            CartItem cartItem = cartService.getCartItemByUserAndProduct(user, product);

            // Vérifiez si le CartItem existe
            if (cartItem != null) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(order);
                orderProduct.setProduct(product);

                // Récupérez la quantité à partir du CartItem
                orderProduct.setQuantity(cartItem.getQuantity());

                orderProducts.add(orderProduct);
            }
        }

        order.setOrderProducts(orderProducts);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersWithOrderProducts() {
        return orderRepository.findAllWithOrderProducts();
    }

    public void removeOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public double calculateTotalCost(Order order) {
        double totalCost = 0.0;

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            totalCost += orderProduct.getProduct().getPrice() * orderProduct.getQuantity();
        }

        return totalCost;
    }
}
