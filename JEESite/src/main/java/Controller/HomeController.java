package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import Entity.*;
import org.hibernate.Session;

@WebServlet(name = "HomeController", value = "/HomeController")
public class HomeController extends HttpServlet {
    private static final int n = 2;
    private static final int l = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        List<Integer> listC = dao.getRandomCategory(l);
        int c1 = listC.get(0);
        int c2 = listC.get(1);
        int c3 = listC.get(2);
        List<Product> list1 = dao.getRandomProductsCategory(n,c1);
        List<Product> list2 = dao.getRandomProductsCategory(n,c2);
        List<Product> list3 = dao.getRandomProductsCategory(n,c3);

        request.setAttribute("list1", list1);
        request.setAttribute("list2", list2);
        request.setAttribute("list3", list3);

        List<Category> list_category= new ArrayList<Category>();
        list_category=new CategoryDAO().getAllCategories();
        request.setAttribute("list_category",list_category);

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

