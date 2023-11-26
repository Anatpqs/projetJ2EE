package Controller;

import Entity.Category;
import Entity.User;
import dao.CategoryDAO;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/categoryServlet")

public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        if ("Supprimer".equals(action)) {
            int idCategory = Integer.parseInt(request.getParameter("categoryId"));
            new CategoryDAO().deleteCategory(idCategory);
        } else if ("Add".equals(action)) {

            String category_name = request.getParameter("category_name");
            Category category = new Category();
            category.setName(category_name);
            new CategoryDAO().addCategory(category);
        }

        // Redirigez vers la page appropriée après le traitement du formulaire
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {    List<Category> categories = new ArrayList<Category>();
        categories = new CategoryDAO().getAllCategories();

        request.setAttribute("categories",categories);

        RequestDispatcher categoriesDispatcher= request.getRequestDispatcher("adminCategory.jsp");
        categoriesDispatcher.forward(request,response);
    }
}

