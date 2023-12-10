package com.mycompany.Controller;

import com.mycompany.Entity.*;
import com.mycompany.Sevice.CategoriesService;
import com.mycompany.Sevice.ProductService;
import com.mycompany.Exception.NotFoundException;
import jdk.jfr.Category;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/home")
    public String index(Model model) {
        List<Product> productList = productService.listAll();
        List<Categories> categories = categoriesService.listAll();
        model.addAttribute("products", productList);
        model.addAttribute("categories", categories);
        return "index";
    }

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
