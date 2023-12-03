package com.mycompany.Controller;


import com.mycompany.Entity.*;
import com.mycompany.Sevice.CartService;
import com.mycompany.Sevice.OrderService;
import com.mycompany.Sevice.CategoriesService;
import com.mycompany.Sevice.ProductService;
import com.mycompany.Sevice.UserService;
import com.mycompany.Exception.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpSession session;

    @Autowired
    private CartService cartService;


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



    @PostMapping("/payment")
    public String goToPayment(Model model, HttpSession session) {
        // Get the username (or other identifier) of the currently authenticated user
        Integer userId = (Integer) session.getAttribute("userId");

        // Check if the user is not null before proceeding
        if (userId != null) {
            Optional<User> userOptional = userService.findById(userId);

            // Retrieve cart items for the user
            List<Product> cartItems = cartService.getCartItems(userOptional.orElse(null));

            // Calculate total amount based on cart items
            double totalAmount = cartService.calculateTotalAmount(cartItems);

            // Add totalAmount to the model
            model.addAttribute("totalAmount", totalAmount);

            // Return the name of your payment view HTML
            return "payment";
        } else {
            // Handle the case where the user is not found
            // You might redirect to a login page or show an error message
            return "redirect:/login";
        }
    }

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

    @GetMapping("/home")
    public String index(Model model) {
        List<Product> productList = productService.listAll();
        model.addAttribute("products", productList);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/homeLogin")
    public String homeLogin(Model model) {
        List<Product> productList = productService.listAll();
        model.addAttribute("products", productList);
        return "homeLogin";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<User> userOptional = userService.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Vérification du mot de passe (sans hachage sécurisé, uniquement à des fins démonstratives)
            if (user.getPassword().equals(password)) {
                // Stockez le nom de l'utilisateur dans la session
                session.setAttribute("userFullName", user.getFirstName() + " " + user.getLastName());
                session.setAttribute("userId", user.getId());

                if (user.getRightAdmin()) {
                    // Utilisateur administrateur, redirigez-le vers la page d'administration
                    return "redirect:/admin";
                } else {
                    // Utilisateur normal, redirigez-le vers la page principale
                    return "redirect:/homeLogin";
                }
            } else {
                model.addAttribute("error", "Mot de passe incorrect");
                return "/login_form";
            }
        } else {
            model.addAttribute("error", "Aucun utilisateur trouvé avec cet e-mail");
            return "/login_form";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        // Vous pouvez ajouter des validations supplémentaires ici si nécessaire

        // Assurez-vous que la propriété loyaltyPoint est initialisée à zéro
        user.setLoyaltyPoint(0.0);

        // Enregistrez l'utilisateur dans la base de données (vous devez implémenter cette méthode dans le service)
        userService.save(user);

        // Redirigez l'utilisateur vers une page de confirmation ou de connexion
        return "redirect:/login";
    }


    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.save(user);

        return "register_success";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    //Category Section
    @GetMapping("/admin/categories")
    public String showCategoriesList(Model model) {
        List<Categories> listCategories = categoriesService.listAll();
        model.addAttribute("listCategories", listCategories);
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String shawCategoriesAdd(Model model) {
        model.addAttribute("categories", new Categories());
        model.addAttribute("pageTitle", "Add New Categories");
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/save")
    public String saveCategories(Categories categories, RedirectAttributes ra) {
        categoriesService.save(categories);
        ra.addFlashAttribute("message", "The category has been saved successfully.");
        return "redirect:/admin/categories";
    }

    @GetMapping("admin/categories/edit/{id}")
    public String showEditCategories(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) throws NotFoundException {
        try {
            Categories categories = categoriesService.get(id);
            model.addAttribute("categories", categories);
            model.addAttribute("pageTitle", "Edit Categories (ID: " + id + ")");
            return "categoriesAdd";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/categories";
        }
    }

    @GetMapping("admin/categories/delete/{id}")
    public String deleteCategories(@PathVariable("id") Integer id, RedirectAttributes ra) throws NotFoundException {
        try {
            categoriesService.delete(id);
            ra.addFlashAttribute("message", "The category ID " + id + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    //User Section
    @GetMapping("/admin/users")
    public String showUserList(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/admin/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/admin/users/save")
    public String saveUsers(User user, RedirectAttributes ra) {
        userService.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/admin/users";
    }

    @GetMapping("admin/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) throws NotFoundException {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "user_form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/users";
        }
    }

    @GetMapping("admin/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) throws NotFoundException {
        try {
            userService.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/users";
    }

    //Product Section
    @GetMapping("/admin/products")
    public String showProductList(Model model) {
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String shawProductAdd(Model model) {
        model.addAttribute("products", new Product());
        model.addAttribute("categories", categoriesService.listAll());
        model.addAttribute("pageTitle", "Add New Products");
        return "productsAdd";
    }

    @PostMapping("/admin/products/save")
    public String saveProducts(Product product, RedirectAttributes ra) {
        productService.save(product);
        ra.addFlashAttribute("message", "The category has been saved successfully.");
        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/edit/{id}")
    public String showEditProduct(@PathVariable("id") Long id, Model model, RedirectAttributes ra) throws NotFoundException {
        try {
            Product product = productService.get(id);
            model.addAttribute("products", product);
            model.addAttribute("pageTitle", "Edit Product (ID: " + id + ")");
            return "productsAdd";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/products";
        }
    }


    @GetMapping("admin/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes ra) throws NotFoundException {
        try {
            productService.delete(id);
            ra.addFlashAttribute("message", "The product ID " + id + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/products";
    }
}
