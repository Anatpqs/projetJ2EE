package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Entity.Category;
import Entity.Product;
import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ProductListController", value = "/ProductListController")
public class ProductListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
        String name = request.getParameter("name");
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getProductBySearch(name,idCategory);
        request.setAttribute("list", list);

        List<Category> list_category= new ArrayList<Category>();
        list_category=new CategoryDAO().getAllCategories();
        request.setAttribute("list_category",list_category);

        RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}