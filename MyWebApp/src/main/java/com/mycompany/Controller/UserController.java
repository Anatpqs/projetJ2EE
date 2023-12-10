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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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


    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/homeLogin")
    public String homeLogin(Model model) {
        List<Product> productList = productService.listAll();
        List<Categories> categories = categoriesService.listAll();
        model.addAttribute("products", productList);
        model.addAttribute("categories", categories);
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



}
