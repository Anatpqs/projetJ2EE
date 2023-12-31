package Controller;

import dao.BasketDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Entity.*;
import java.io.IOException;

@WebServlet("/BasketController")
public class BasketController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");


        int productId = Integer.parseInt(request.getParameter("productId"));


        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);


        BasketDAO basketDAO = new BasketDAO();
        Basket basket = new Basket();
        basket.setIdUser(currentUser.getIdUser());
        basket.setIdProduct(productId);
        basket.setQuantity(1);
        basketDAO.addToBasket(basket);

        response.sendRedirect("ProductController?idProduct=" + productId);
    }
}
