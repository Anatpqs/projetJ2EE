package Controller;

import java.io.*;
import java.util.List;

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        List<Product> listM = dao.getRandomProductsCategory(n,"M");
        List<Product> listW = dao.getRandomProductsCategory(n,"W");
        List<Product> listK = dao.getRandomProductsCategory(n,"K");

        request.setAttribute("listM", listM);
        request.setAttribute("listW", listW);
        request.setAttribute("listK", listK);

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}