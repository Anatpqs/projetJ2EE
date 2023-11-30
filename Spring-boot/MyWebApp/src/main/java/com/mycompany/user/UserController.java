package com.mycompany.user;

import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UserController {
    private static String uploadDir = System.getProperty("user.dir") + "/src/main/ressources/static/productImages";
    @Autowired
    private UserService userService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ProductService productService;



    @GetMapping("/home")
    public String home() {
        return "index";
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
    public String saveProducts(@ModelAttribute("products")Product product,
                               @RequestParam("image")MultipartFile file,
                               @RequestParam("imgName")String imgName,
                               RedirectAttributes ra) throws IOException {

        productService.save(product);
        ra.addFlashAttribute("message", "The category has been saved successfully.");

        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/edit/{id}")
    public String showEditProduct(@PathVariable("id") Long id, Model model, RedirectAttributes ra) throws NotFoundException {
        try {
            Product product = productService.get(id);
            model.addAttribute("product", product);
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
