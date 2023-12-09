package Controller;

import dao.BasketDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Entity.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Entity.*;


@WebServlet("/updateQuantityServlet")
public class updateQuantityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int basketId = Integer.parseInt(request.getParameter("basketId"));
        int newQuantity=0;
        BasketDAO basketDAO = new BasketDAO();
        Basket basket = basketDAO.getBasketById(basketId);

        if ("increment".equals(action)) {
            newQuantity = basket.getQuantity() + 1;
        } else if ("decrement".equals(action)) {
            newQuantity = basket.getQuantity() - 1;
        }

        if (newQuantity > 0) {
            basket.setQuantity(newQuantity);
        } else {
            basketDAO.RemoveBasketsById(basket.getIdBasket());
        }

        basketDAO.updateBasket(basket);
        response.sendRedirect(request.getContextPath() + "/Basket.jsp");
    }

}
